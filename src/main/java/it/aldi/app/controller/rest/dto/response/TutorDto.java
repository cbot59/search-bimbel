package it.aldi.app.controller.rest.dto.response;

import it.aldi.app.domain.BimbelUser;
import lombok.Value;

import java.util.Arrays;
import java.util.List;

@Value
public class TutorDto {
    private String name;
    private String email;
    private String username;
    private List<String> subjects;
    private String phone;
    private String address;
    private String otherNote;

    private TutorDto(BimbelUser bimbelUser) {
        name = bimbelUser.getName();
        email = bimbelUser.getEmail();
        username = bimbelUser.getEmail();
        // TODO: get subject from entity relation
        subjects = Arrays.asList("Matematika", "Fisika");
        phone = bimbelUser.getBimbelUserDetails().getPhone();
        address = bimbelUser.getBimbelUserDetails().getAddress();
        otherNote = "Belum ada isi";
    }

    public static TutorDto valueOf(BimbelUser bimbelUser) {
        return new TutorDto(bimbelUser);
    }
}
