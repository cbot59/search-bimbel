package it.aldi.app.service.subject_management;

import it.aldi.app.controller.rest.dto.request.AddSubjectCmd;
import it.aldi.app.domain.Organization;

public interface SubjectManagementService {
    void saveSubject(AddSubjectCmd cmd, Organization organization);
}
