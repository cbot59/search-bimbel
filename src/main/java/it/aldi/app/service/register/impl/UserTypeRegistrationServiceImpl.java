package it.aldi.app.service.register.impl;

import it.aldi.app.domain.*;
import it.aldi.app.service.domain.OrganizationService;
import it.aldi.app.service.domain.OwnerService;
import it.aldi.app.service.domain.StudentService;
import it.aldi.app.service.domain.TutorService;
import it.aldi.app.service.register.UserTypeRegistrationService;
import it.aldi.app.util.RoleConstant;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserTypeRegistrationServiceImpl implements UserTypeRegistrationService {

    private final OrganizationService organizationService;

    private final OwnerService ownerService;

    private final StudentService studentService;

    private final TutorService tutorService;

    public UserTypeRegistrationServiceImpl(OrganizationService organizationService,
                                           OwnerService ownerService,
                                           StudentService studentService,
                                           TutorService tutorService) {
        this.organizationService = organizationService;
        this.ownerService = ownerService;
        this.studentService = studentService;
        this.tutorService = tutorService;
    }

    @Override
    public void registerUserType(BimbelUser bimbelUser) {
        switch (bimbelUser.getBimbelUserType().getName()) {
            case RoleConstant.OWNER:
                registerOwner(bimbelUser);
                break;
            case RoleConstant.STUDENT:
                registerStudent(bimbelUser);
                break;
            case RoleConstant.TUTOR:
                registerTutor(bimbelUser);
                break;
            default:
                throw new IllegalStateException("Unexpected userType: " + bimbelUser.getBimbelUserType());
        }
    }

    private void registerOwner(BimbelUser bimbelUser) {
        Optional<Owner> owner = ownerService.findByUserId(bimbelUser.getId());

        if (owner.isPresent()) {
            throw new IllegalArgumentException("Owner already exists: " + owner.get());
        }

        Organization organization = organizationService.save(Organization.createDefault(bimbelUser.getName()));

        ownerService.save(Owner.initialize(organization, bimbelUser));
    }

    private void registerStudent(BimbelUser bimbelUser) {
        Optional<Student> student = studentService.findByUserId(bimbelUser.getId());

        if (student.isPresent()) {
            throw new IllegalArgumentException("Student already exists: " + student.get());
        }

        studentService.save(Student.initialize(bimbelUser));
    }

    private void registerTutor(BimbelUser bimbelUser) {
        Optional<Tutor> tutor = tutorService.findByUserId(bimbelUser.getId());

        if (tutor.isPresent()) {
            throw new IllegalArgumentException("Tutor already exists: " + tutor.get());
        }

        tutorService.save(Tutor.initialize(bimbelUser));
    }
}
