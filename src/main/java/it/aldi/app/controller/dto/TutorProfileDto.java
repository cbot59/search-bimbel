package it.aldi.app.controller.dto;

import it.aldi.app.domain.Tutor;
import lombok.Value;

@Value
public class TutorProfileDto {
    private String name;
    private String username;
    private String email;
    private String phone;
    private String address;

    private TutorProfileDto(Tutor tutor) {
        name = tutor.getChairman().getOrganization().getName();
        username = tutor.getBimbelUser().getUsername();
        email = tutor.getBimbelUser().getEmail();
        phone = tutor.getBimbelUser().getBimbelUserDetails().getPhone();
        address = tutor.getBimbelUser().getBimbelUserDetails().getAddress();
    }

    public static TutorProfileDto from(Tutor tutor) {
        return new TutorProfileDto(tutor);
    }
}
