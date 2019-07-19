package it.aldi.app.controller.rest.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddJobCmd {

    @NotNull
    private String name;

    @NotNull
    private Integer age;

    private String otherNote;

    private Long organizationId;
}
