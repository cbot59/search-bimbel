package it.aldi.app.controller.dto;

import it.aldi.app.domain.Student;
import lombok.Value;

@Value
public class StudentProfileDto {
    private String name;
    private String username;
    private String email;
    private String phone;
    private String address;

    private StudentProfileDto(Student student) {
        name = student.getBimbelUser().getName();
        username = student.getBimbelUser().getUsername();
        email = student.getBimbelUser().getEmail();
        phone = student.getBimbelUser().getBimbelUserDetails().getPhone();
        address = student.getBimbelUser().getBimbelUserDetails().getAddress();
    }

    public static StudentProfileDto from(Student student) {
        return new StudentProfileDto(student);
    }
}
