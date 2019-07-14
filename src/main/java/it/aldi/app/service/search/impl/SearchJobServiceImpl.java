package it.aldi.app.service.search.impl;

import it.aldi.app.controller.rest.dto.response.SearchJobDto;
import it.aldi.app.service.domain.JobService;
import it.aldi.app.service.search.SearchJobService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class SearchJobServiceImpl implements SearchJobService {

    private final JobService jobService;

    public SearchJobServiceImpl(JobService jobService) {
        this.jobService = jobService;
    }

    @Override
    public Page<SearchJobDto> searchJob(Pageable pageable) {
        return jobService.findAll(pageable)
            .map(SearchJobDto::from);
    }
}
