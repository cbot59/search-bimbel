package it.aldi.app.service.manage_job;

import it.aldi.app.controller.rest.dto.request.AddJobCmd;

public interface JobManagementService {
    void addJob(AddJobCmd addJobCmd, Long organizationId);
}
