package it.aldi.app.controller.rest.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import it.aldi.app.domain.SubjectType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddSubjectCmd {

    @NotNull
    private String name;

    private String description;

    @NotNull
    private String level;

    @JsonIgnore
    private SubjectType subjectType;

    public AddSubjectCmd subjectType(SubjectType type) {
        subjectType = type;
        return this;
    }
}
