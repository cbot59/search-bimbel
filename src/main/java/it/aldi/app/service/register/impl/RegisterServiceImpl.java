package it.aldi.app.service.register.impl;

import it.aldi.app.controller.cmd.RegisterUserCmd;
import it.aldi.app.domain.*;
import it.aldi.app.service.domain.BimbelUserService;
import it.aldi.app.service.domain.BimbelUserTypeRoleService;
import it.aldi.app.service.domain.BimbelUserTypeService;
import it.aldi.app.service.domain.RoleService;
import it.aldi.app.service.register.RegisterService;
import it.aldi.app.service.register.UserTypeRegistrationService;
import it.aldi.app.util.ErrorMsgConstant;
import it.aldi.app.util.RoleConstant;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RegisterServiceImpl implements RegisterService {
    private static final String SUPER_ADMIN = "SUPER_ADMIN";
    private static final String OWNER = "OWNER";

    private final ErrorMsgConstant errorMsgConstant;

    private final BimbelUserService bimbelUserService;

    private final BimbelUserTypeService bimbelUserTypeService;

    private final BimbelUserTypeRoleService bimbelUserTypeRoleService;

    private final RoleService roleService;

    private final UserTypeRegistrationService userTypeRegistrationService;

    public RegisterServiceImpl(ErrorMsgConstant errorMsgConstant,
                               BimbelUserService bimbelUserService,
                               BimbelUserTypeService bimbelUserTypeService,
                               BimbelUserTypeRoleService bimbelUserTypeRoleService,
                               RoleService roleService,
                               UserTypeRegistrationService userTypeRegistrationService) {
        this.errorMsgConstant = errorMsgConstant;
        this.bimbelUserService = bimbelUserService;
        this.bimbelUserTypeService = bimbelUserTypeService;
        this.bimbelUserTypeRoleService = bimbelUserTypeRoleService;
        this.roleService = roleService;
        this.userTypeRegistrationService = userTypeRegistrationService;
    }

    @Override
    public void registerUser(RegisterUserCmd cmd) {
        BimbelUserType bimbelUserType = bimbelUserTypeService.findOne(cmd.getRoles());
        Role role = roleService.findOne(cmd.getRoles());

        BimbelUserTypeRole bimbelUserTypeRole = BimbelUserTypeRole.from(bimbelUserType, role);
        BimbelUserDetails bimbelUserDetails = BimbelUserDetails.from(cmd);
        BimbelUser bimbelUser = BimbelUser.register(cmd)
            .bimbelUserType(bimbelUserType)
            .bimbelUserDetails(bimbelUserDetails);

        bimbelUserTypeRoleService.save(bimbelUserTypeRole);
        BimbelUser savedBimbelUser = bimbelUserService.save(bimbelUser);

        userTypeRegistrationService.registerUserType(savedBimbelUser);
    }

    @Override
    public List<Role> getPublicRoles() {
        return roleService.findAll().stream()
            .filter(role -> !SUPER_ADMIN.equalsIgnoreCase(role.getName()))
            .collect(Collectors.toList());
    }

    @Override
    public String verifyExistingData(RegisterUserCmd cmd) {
        if (bimbelUserService.findByUsername(cmd.getUsername()) != null) {
            return errorMsgConstant.getUsernameExists();
        }

        if (bimbelUserService.findByEmail(cmd.getEmail()) != null) {
            return errorMsgConstant.getEmailExists();
        }

        return "";
    }

    private Set<Role> assignRoles(RegisterUserCmd cmd) {
        List<Role> availableRoles = roleService.findAll();
        switch (cmd.getRoles()) {
            case SUPER_ADMIN:
                return everyRoles(availableRoles);
            case OWNER:
                return managementRoles(availableRoles);
            default:
                return singleRole(cmd, availableRoles);
        }
    }

    private static Set<Role> singleRole(RegisterUserCmd cmd, List<Role> availableRoles) {
        return availableRoles.stream()
            .filter(role -> role.getName().equalsIgnoreCase(cmd.getRoles()))
            .collect(Collectors.toSet());
    }

    private static Set<Role> managementRoles(List<Role> availableRoles) {
        return availableRoles.stream()
            .filter(RegisterServiceImpl::isManagementRole)
            .collect(Collectors.toSet());
    }

    private static boolean isManagementRole(Role role) {
        return role.getName().equalsIgnoreCase(RoleConstant.OWNER)
            || role.getName().equalsIgnoreCase(RoleConstant.TUTOR);
    }

    private static Set<Role> everyRoles(List<Role> availableRoles) {
        return new HashSet<>(availableRoles);
    }
}
