package it.aldi.app.service.manage_subject.impl;

import it.aldi.app.controller.rest.dto.request.SubjectCmd;
import it.aldi.app.controller.rest.dto.response.SubjectDto;
import it.aldi.app.domain.Organization;
import it.aldi.app.domain.Subject;
import it.aldi.app.domain.SubjectType;
import it.aldi.app.service.domain.SubjectService;
import it.aldi.app.service.domain.SubjectTypeService;
import it.aldi.app.service.manage_subject.ManageSubjectService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManageSubjectServiceImpl implements ManageSubjectService {

    private final SubjectService subjectService;

    private final SubjectTypeService subjectTypeService;

    public ManageSubjectServiceImpl(SubjectService subjectService,
                                    SubjectTypeService subjectTypeService) {
        this.subjectService = subjectService;
        this.subjectTypeService = subjectTypeService;
    }

    @Override
    public SubjectDto addSubject(SubjectCmd subjectCmd, Organization organization) {
        Subject cmd = Subject.from(assignSubjectType(subjectCmd))
            .organization(organization);

        return SubjectDto.from(subjectService.save(cmd));
    }

    private SubjectCmd assignSubjectType(SubjectCmd subjectCmd) {
        List<SubjectType> subjectTypes = subjectTypeService.findAll();

        return subjectTypes.stream()
            .filter(subjectType -> subjectCmd.getLevel().equals(subjectType.getName()))
            .findFirst()
            .map(subjectCmd::subjectType)
            .orElseThrow(() -> new IllegalArgumentException("SubjectType is not available: " + subjectCmd.getLevel()));
    }
}
