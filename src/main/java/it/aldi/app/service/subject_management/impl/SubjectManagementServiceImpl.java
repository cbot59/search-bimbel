package it.aldi.app.service.subject_management.impl;

import it.aldi.app.controller.rest.dto.request.AddSubjectCmd;
import it.aldi.app.controller.rest.dto.response.SubjectDto;
import it.aldi.app.domain.Organization;
import it.aldi.app.domain.Subject;
import it.aldi.app.domain.SubjectType;
import it.aldi.app.service.domain.SubjectService;
import it.aldi.app.service.domain.SubjectTypeService;
import it.aldi.app.service.subject_management.SubjectManagementService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectManagementServiceImpl implements SubjectManagementService {

    private final SubjectService subjectService;

    private final SubjectTypeService subjectTypeService;

    public SubjectManagementServiceImpl(SubjectService subjectService,
                                        SubjectTypeService subjectTypeService) {
        this.subjectService = subjectService;
        this.subjectTypeService = subjectTypeService;
    }

    @Override
    public SubjectDto addSubject(AddSubjectCmd cmd, Organization organization) {
        Subject subject = Subject.from(assignSubjectType(cmd))
            .organization(organization);

        return SubjectDto.from(subjectService.save(subject));
    }

    private AddSubjectCmd assignSubjectType(AddSubjectCmd cmd) {
        List<SubjectType> subjectTypes = subjectTypeService.findAll();

        return subjectTypes.stream()
            .filter(subjectType -> cmd.getLevel().equals(subjectType.getName()))
            .findFirst()
            .map(cmd::subjectType)
            .orElseThrow(() -> new IllegalArgumentException("SubjectType is not available: " + cmd.getLevel()));
    }
}
