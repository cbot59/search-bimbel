package it.aldi.app.controller.rest.dto.response;

import it.aldi.app.domain.Job;
import lombok.Value;

@Value
public class JobDto {
    private String name;
    private Integer age;
    private String otherNote;

    private JobDto(Job job) {
        name = job.getName();
        age = job.getAge();
        otherNote = job.getOtherNote();
    }

    public static JobDto from(Job job) {
        return new JobDto(job);
    }
}
