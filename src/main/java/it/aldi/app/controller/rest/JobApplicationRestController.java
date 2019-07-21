package it.aldi.app.controller.rest;

import it.aldi.app.controller.Routes;
import it.aldi.app.controller.cmd.ApproveJobApplicationCmd;
import it.aldi.app.controller.rest.dto.response.JobApplicationDto;
import it.aldi.app.service.domain.JobApplicationService;
import it.aldi.app.service.job_management.JobManagementService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
public class JobApplicationRestController {

    private final JobApplicationService jobApplicationService;

    private final JobManagementService jobManagementService;

    public JobApplicationRestController(JobApplicationService jobApplicationService,
                                        JobManagementService jobManagementService) {
        this.jobApplicationService = jobApplicationService;
        this.jobManagementService = jobManagementService;
    }

    @GetMapping(Routes.API_ORGANIZATIONS_JOB_APPLICATIONS)
    public ResponseEntity<List<JobApplicationDto>> getJobApplicationsByOrgId(@PathVariable("orgId") Long id) {
        return ResponseEntity.status(HttpStatus.OK)
            .body(jobApplicationService.findAll().stream()
                .map(JobApplicationDto::from)
                .collect(Collectors.toList()));
    }

    @PatchMapping(Routes.API_ORGANIZATIONS_JOB_APPLICATIONS_APPROVE)
    public ResponseEntity<Void> approveJobApplication(@PathVariable Long jobAppId,
                                                      @RequestBody ApproveJobApplicationCmd cmd) {
        log.debug("Approving job: {}", cmd);
        jobManagementService.approveJobApplication(cmd);

        return ResponseEntity.noContent().build();
    }
}
