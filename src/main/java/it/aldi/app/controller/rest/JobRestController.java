package it.aldi.app.controller.rest;

import it.aldi.app.controller.Routes;
import it.aldi.app.controller.rest.dto.response.JobDto;
import it.aldi.app.service.domain.JobService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JobRestController {

    private final JobService jobService;

    public JobRestController(JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping(Routes.API_JOBS)
    public ResponseEntity<Page<JobDto>> getJobs(Pageable pageable, @RequestParam("search[value]") String search) {
        return ResponseEntity.status(HttpStatus.OK)
            .body(jobService.findAll(pageable, search).map(JobDto::from));
    }

    @GetMapping(Routes.API_ORGANIZATIONS_JOBS)
    public ResponseEntity<Page<JobDto>> getJobsByOrgId(@PathVariable("orgId") Long id, Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK)
            .body(jobService.findAll(pageable).map(JobDto::from));
    }
}
