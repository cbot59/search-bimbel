package it.aldi.app.service.job_management;

import it.aldi.app.controller.cmd.ApproveJobApplicationCmd;
import it.aldi.app.controller.rest.dto.request.AddJobCmd;

public interface JobManagementService {
    void saveJob(AddJobCmd addJobCmd);

    void approveJobApplication(ApproveJobApplicationCmd cmd);
}
