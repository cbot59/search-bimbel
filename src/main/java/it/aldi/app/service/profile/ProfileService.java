package it.aldi.app.service.profile;

import it.aldi.app.controller.cmd.EditOwnerProfileCmd;
import it.aldi.app.controller.dto.OwnerProfileDto;
import it.aldi.app.domain.BimbelUser;

public interface ProfileService {
    OwnerProfileDto getOwnerProfile(BimbelUser bimbelUser);

    void editOwnerProfile(EditOwnerProfileCmd editOwnerProfileCmd, BimbelUser owner);
}
