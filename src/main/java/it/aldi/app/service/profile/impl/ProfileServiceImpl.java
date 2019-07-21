package it.aldi.app.service.profile.impl;

import it.aldi.app.controller.cmd.EditOwnerProfileCmd;
import it.aldi.app.controller.dto.OwnerProfileDto;
import it.aldi.app.domain.BimbelUser;
import it.aldi.app.domain.Owner;
import it.aldi.app.service.domain.BimbelUserDetailsService;
import it.aldi.app.service.domain.OrganizationService;
import it.aldi.app.service.domain.OwnerService;
import it.aldi.app.service.profile.ProfileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ProfileServiceImpl implements ProfileService {

    private final OwnerService ownerService;

    private final BimbelUserDetailsService bimbelUserDetailsService;

    private final OrganizationService organizationService;

    public ProfileServiceImpl(OwnerService ownerService,
                              BimbelUserDetailsService bimbelUserDetailsService,
                              OrganizationService organizationService) {
        this.ownerService = ownerService;
        this.bimbelUserDetailsService = bimbelUserDetailsService;
        this.organizationService = organizationService;
    }

    @Override
    public OwnerProfileDto getOwnerProfile(BimbelUser bimbelUser) {
        Owner owner = ownerService.findByUserId(bimbelUser.getId())
            .orElseThrow(() -> new IllegalArgumentException("Owner not found for user: " + bimbelUser));

        return OwnerProfileDto.from(owner);
    }

    @Override
    public void editOwnerProfile(EditOwnerProfileCmd editOwnerProfileCmd, BimbelUser bimbelUser) {
        Owner owner = ownerService.findByUserId(bimbelUser.getId())
            .orElseThrow(() -> new IllegalArgumentException("Owner not found for user: " + bimbelUser));

        organizationService.save(owner.getChairman().getOrganization()
            .name(editOwnerProfileCmd.getOrgName()));

        bimbelUserDetailsService.save(owner.getBimbelUser().getBimbelUserDetails()
            .phone(editOwnerProfileCmd.getPhone())
            .address(editOwnerProfileCmd.getAddress()));
    }
}
