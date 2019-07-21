package it.aldi.app.controller.rest.dto.response;

import it.aldi.app.domain.Job;
import lombok.Value;

@Value
public class JobDto {
    private Long jobId;
    private String name;
    private Integer age;
    private String otherNote;
    private String organizationName;

    private JobDto(Job job) {
        jobId = job.getId();
        name = job.getName();
        age = job.getAge();
        otherNote = job.getOtherNote();
        // TODO: business model changed, job is no longer used
        organizationName = "";
    }

    public static JobDto from(Job job) {
        return new JobDto(job);
    }
}
