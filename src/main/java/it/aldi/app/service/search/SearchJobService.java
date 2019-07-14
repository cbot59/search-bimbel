package it.aldi.app.service.search;

import it.aldi.app.controller.rest.dto.response.SearchJobDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SearchJobService {

    Page<SearchJobDto> searchJob(Pageable pageable);
}
