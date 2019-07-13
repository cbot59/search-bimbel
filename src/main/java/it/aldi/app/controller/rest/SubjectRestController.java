package it.aldi.app.controller.rest;

import it.aldi.app.controller.Routes;
import it.aldi.app.controller.rest.dto.response.SubjectDto;
import it.aldi.app.service.domain.SubjectService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(Routes.API_SUBJECTS)
public class SubjectRestController {

    private final SubjectService subjectService;

    public SubjectRestController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @GetMapping("/{orgId}")
    public ResponseEntity<Page<SubjectDto>> getSubjectsByOrgId(@PathVariable("orgId") Long id, Pageable pageable) {
        // TODO: adjust pageable to be paginated from frontend

        return ResponseEntity.status(HttpStatus.OK)
            .body(subjectService.findAll(pageable).map(SubjectDto::from));
    }
}
