package it.aldi.app.service.job_management;

import it.aldi.app.controller.rest.dto.request.AddJobCmd;
import it.aldi.app.domain.Organization;

public interface JobManagementService {
    void addJob(AddJobCmd addJobCmd, Organization organization);
}
