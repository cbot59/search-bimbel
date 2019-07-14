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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(Routes.API_JOBS)
public class JobRestController {

    private final JobService jobService;

    public JobRestController(JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping("/{orgId}")
    public ResponseEntity<Page<JobDto>> getJobsByOrgId(@PathVariable("orgId") Long id, Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK)
            .body(jobService.findAll(pageable).map(JobDto::from));
    }
}
