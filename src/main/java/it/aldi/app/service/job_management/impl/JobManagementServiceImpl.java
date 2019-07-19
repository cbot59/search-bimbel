package it.aldi.app.service.job_management.impl;

import it.aldi.app.controller.cmd.ApproveJobApplicationCmd;
import it.aldi.app.controller.rest.dto.request.AddJobCmd;
import it.aldi.app.domain.Job;
import it.aldi.app.domain.JobApplication;
import it.aldi.app.domain.Organization;
import it.aldi.app.service.domain.JobApplicationService;
import it.aldi.app.service.domain.JobService;
import it.aldi.app.service.domain.OrganizationService;
import it.aldi.app.service.job_management.JobManagementService;
import org.springframework.stereotype.Service;

@Service
public class JobManagementServiceImpl implements JobManagementService {

    private final JobService jobService;

    private final OrganizationService organizationService;

    private final JobApplicationService jobApplicationService;

    public JobManagementServiceImpl(JobService jobService,
                                    OrganizationService organizationService,
                                    JobApplicationService jobApplicationService) {
        this.jobService = jobService;
        this.organizationService = organizationService;
        this.jobApplicationService = jobApplicationService;
    }

    @Override
    public void saveJob(AddJobCmd cmd) {
        Organization organization = organizationService.findOne(cmd.getOrganizationId());
        Job job = Job.from(cmd).organization(organization);

        jobService.save(job);
    }

    @Override
    public void approveJobApplication(ApproveJobApplicationCmd cmd) {
        JobApplication jobApplication = jobApplicationService.findOne(cmd.getJobAppId());

        jobApplicationService.save(jobApplication.approved(true));
    }
}
