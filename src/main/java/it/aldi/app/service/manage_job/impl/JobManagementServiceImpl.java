package it.aldi.app.service.manage_job.impl;

import it.aldi.app.controller.rest.dto.request.AddJobCmd;
import it.aldi.app.service.domain.JobService;
import it.aldi.app.service.manage_job.JobManagementService;
import org.springframework.stereotype.Service;

@Service
public class JobManagementServiceImpl implements JobManagementService {

    private final JobService jobService;

    public JobManagementServiceImpl(JobService jobService) {
        this.jobService = jobService;
    }

    @Override
    public void addJob(AddJobCmd addJobCmd, Long organizationId) {
        // TODO: implementation
    }
}
