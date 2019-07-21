package it.aldi.app.controller.rest.dto.response;

import it.aldi.app.domain.JobApplication;
import lombok.Value;

@Value
public class JobApplicationDto {
    private Long jobAppId;
    private String jobName;
    private Long tutorId;
    private String tutorName;

    private JobApplicationDto(JobApplication jobApplication) {
        jobAppId = jobApplication.getId();
        jobName = jobApplication.getJob().getName();
        tutorId = jobApplication.getTutor().getId();
        tutorName = jobApplication.getTutor().getBimbelUser().getName();
    }

    public static JobApplicationDto from(JobApplication jobApplication) {
        return new JobApplicationDto(jobApplication);
    }
}
