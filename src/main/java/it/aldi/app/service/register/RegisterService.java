package it.aldi.app.service.register;

import it.aldi.app.controller.cmd.RegisterUserCmd;
import it.aldi.app.domain.Role;

import java.util.List;

public interface RegisterService {
    void registerUser(RegisterUserCmd registerUserCmd);

    List<Role> getPublicRoles();

    String verifyExistingData(RegisterUserCmd registerUserCmd);
}
