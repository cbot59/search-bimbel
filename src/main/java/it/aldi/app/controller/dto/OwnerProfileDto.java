package it.aldi.app.controller.dto;

import it.aldi.app.domain.Owner;
import lombok.Value;

@Value
public class OwnerProfileDto {
    private String name;
    private String username;
    private String email;
    private String phone;
    private String address;

    private OwnerProfileDto(Owner owner) {
        name = owner.getChairman().getOrganization().getName();
        username = owner.getBimbelUser().getUsername();
        email = owner.getBimbelUser().getEmail();
        phone = owner.getBimbelUser().getBimbelUserDetails().getPhone();
        address = owner.getBimbelUser().getBimbelUserDetails().getAddress();
    }

    public static OwnerProfileDto from(Owner owner) {
        return new OwnerProfileDto(owner);
    }
}
