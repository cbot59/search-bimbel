package it.aldi.app.controller.rest.dto.response;

import it.aldi.app.domain.Subject;
import lombok.Value;

@Value
public class SubjectDto {
    private String name;
    private String description;
    private String level;

    public SubjectDto(Subject subject) {
        name = subject.getName();
        description = subject.getDescription();
        level = subject.getSubjectType().getName();
    }

    public static SubjectDto from(Subject subject) {
        return new SubjectDto(subject);
    }
}
