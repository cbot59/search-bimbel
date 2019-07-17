package it.aldi.app.controller.rest;

import it.aldi.app.controller.Routes;
import it.aldi.app.controller.rest.dto.response.JobApplicationDto;
import it.aldi.app.service.domain.JobApplicationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class JobApplicationRestController {

    private final JobApplicationService jobApplicationService;

    public JobApplicationRestController(JobApplicationService jobApplicationService) {
        this.jobApplicationService = jobApplicationService;
    }

    @GetMapping(Routes.API_ORGANIZATIONS_JOB_APPLICATIONS)
    public ResponseEntity<List<JobApplicationDto>> getJobApplicationsByOrgId(@PathVariable("orgId") Long id) {
        return ResponseEntity.status(HttpStatus.OK)
            .body(jobApplicationService.findAll(id).stream()
                .map(JobApplicationDto::from)
                .collect(Collectors.toList()));
    }
}
