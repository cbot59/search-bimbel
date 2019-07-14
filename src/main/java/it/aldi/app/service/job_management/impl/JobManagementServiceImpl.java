package it.aldi.app.service.job_management.impl;

import it.aldi.app.controller.rest.dto.request.AddJobCmd;
import it.aldi.app.domain.Job;
import it.aldi.app.domain.Organization;
import it.aldi.app.service.domain.JobService;
import it.aldi.app.service.job_management.JobManagementService;
import org.springframework.stereotype.Service;

@Service
public class JobManagementServiceImpl implements JobManagementService {

    private final JobService jobService;

    public JobManagementServiceImpl(JobService jobService) {
        this.jobService = jobService;
    }

    @Override
    public void saveJob(AddJobCmd cmd, Organization organization) {
        Job job = Job.from(cmd).organization(organization);

        jobService.save(job);
    }
}
