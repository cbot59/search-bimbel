package it.aldi.app.service.manage_subject;

import it.aldi.app.controller.rest.dto.request.AddSubjectCmd;
import it.aldi.app.controller.rest.dto.response.SubjectDto;
import it.aldi.app.domain.Organization;

public interface ManageSubjectService {
    SubjectDto addSubject(AddSubjectCmd cmd, Organization organization);
}
