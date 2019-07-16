package it.aldi.app.service.search;

import it.aldi.app.controller.rest.dto.response.SearchJobDto;
import it.aldi.app.domain.Tutor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SearchJobService {

    Page<SearchJobDto> searchJob(Pageable pageable);

    void applyJob(Long jobId, Tutor tutor);
}
