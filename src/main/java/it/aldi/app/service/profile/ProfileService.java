package it.aldi.app.service.profile;

import it.aldi.app.controller.cmd.EditOwnerProfileCmd;
import it.aldi.app.controller.cmd.EditStudentProfileCmd;
import it.aldi.app.controller.cmd.EditTutorProfileCmd;
import it.aldi.app.controller.dto.OwnerProfileDto;
import it.aldi.app.controller.dto.StudentProfileDto;
import it.aldi.app.controller.dto.TutorProfileDto;
import it.aldi.app.domain.BimbelUser;

public interface ProfileService {
    OwnerProfileDto getOwnerProfile(BimbelUser bimbelUser);

    StudentProfileDto getStudentProfile(BimbelUser bimbelUser);

    TutorProfileDto getTutorProfile(BimbelUser bimbelUser);

    void editOwnerProfile(EditOwnerProfileCmd editOwnerProfileCmd, BimbelUser owner);

    void editStudentProfile(EditStudentProfileCmd editStudentProfileCmd, BimbelUser student);

    void editTutorProfile(EditTutorProfileCmd editTutorProfileCmd, BimbelUser tutor);
}
