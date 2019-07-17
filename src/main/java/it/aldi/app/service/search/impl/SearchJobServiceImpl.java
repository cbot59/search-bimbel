package it.aldi.app.service.search.impl;

import it.aldi.app.controller.rest.dto.response.SearchJobDto;
import it.aldi.app.domain.Job;
import it.aldi.app.domain.JobApplication;
import it.aldi.app.domain.Tutor;
import it.aldi.app.service.domain.JobApplicationService;
import it.aldi.app.service.domain.JobService;
import it.aldi.app.service.domain.TutorService;
import it.aldi.app.service.search.SearchJobService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class SearchJobServiceImpl implements SearchJobService {

    private final JobService jobService;

    private final TutorService tutorService;

    private final JobApplicationService jobApplicationService;

    public SearchJobServiceImpl(JobService jobService,
                                TutorService tutorService,
                                JobApplicationService jobApplicationService) {
        this.jobService = jobService;
        this.tutorService = tutorService;
        this.jobApplicationService = jobApplicationService;
    }

    @Override
    public Page<SearchJobDto> searchJob(Pageable pageable) {
        return jobService.findAll(pageable)
            .map(SearchJobDto::from);
    }

    @Override
    public void applyJob(Long jobId, Tutor tutor) {
        Job job = jobService.findOne(jobId)
            .orElseThrow(() -> new IllegalArgumentException("Job not found, id: " + jobId));

        jobApplicationService.save(JobApplication.from(job, tutor));
    }
}
