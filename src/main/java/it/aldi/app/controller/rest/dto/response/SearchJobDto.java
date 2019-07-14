package it.aldi.app.controller.rest.dto.response;

import it.aldi.app.domain.Job;
import lombok.Value;

@Value
public class SearchJobDto {
    private String organizationName;
    private Long jobId;
    private String jobName;
    private Integer age;
    private String otherNote;

    private SearchJobDto(Job job) {
        organizationName = job.getOrganization().getName();
        jobId = job.getId();
        jobName = job.getName();
        age = job.getAge();
        otherNote = job.getOtherNote();
    }

    public static SearchJobDto from(Job job) {
        return new SearchJobDto(job);
    }
}
