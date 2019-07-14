package it.aldi.app.service.register.impl;

import it.aldi.app.controller.dto.BimbelUserDto;
import it.aldi.app.domain.BimbelUser;
import it.aldi.app.domain.BimbelUserType;
import it.aldi.app.domain.BimbelUserTypeRole;
import it.aldi.app.domain.Role;
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
    public void registerUser(BimbelUserDto bimbelUserDto) {
        BimbelUserType bimbelUserType = bimbelUserTypeService.findOne(bimbelUserDto.getRoles());
        Role role = roleService.findOne(bimbelUserDto.getRoles());

        BimbelUserTypeRole bimbelUserTypeRole = BimbelUserTypeRole.from(bimbelUserType, role);
        BimbelUser bimbelUser = BimbelUser.register(bimbelUserDto, bimbelUserType);

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
    public String verifyExistingData(BimbelUserDto bimbelUserDto) {
        if (bimbelUserService.findByUsername(bimbelUserDto.getUsername()) != null) {
            return errorMsgConstant.getUsernameExists();
        }
        if (bimbelUserService.findByEmail(bimbelUserDto.getEmail()) != null) {
            return errorMsgConstant.getEmailExists();
        }
        return "";
    }

    private Set<Role> assignRoles(BimbelUserDto bimbelUserDto) {
        List<Role> availableRoles = roleService.findAll();
        switch (bimbelUserDto.getRoles()) {
            case SUPER_ADMIN:
                return everyRoles(availableRoles);
            case OWNER:
                return managementRoles(availableRoles);
            default:
                return singleRole(bimbelUserDto, availableRoles);
        }
    }

    private static Set<Role> singleRole(BimbelUserDto bimbelUserDto, List<Role> availableRoles) {
        return availableRoles.stream()
            .filter(role -> role.getName().equalsIgnoreCase(bimbelUserDto.getRoles()))
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
