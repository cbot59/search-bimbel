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
public class SubjectCmd {

    @NotNull
    private String name;

    private String description;

    @NotNull
    private String level;

    @JsonIgnore
    private SubjectType subjectType;

    public SubjectCmd subjectType(SubjectType type) {
        subjectType = type;
        return this;
    }
}
