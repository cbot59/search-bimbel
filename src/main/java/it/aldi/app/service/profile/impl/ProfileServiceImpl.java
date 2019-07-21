package it.aldi.app.service.profile.impl;

import it.aldi.app.controller.cmd.EditOwnerProfileCmd;
import it.aldi.app.controller.cmd.EditStudentProfileCmd;
import it.aldi.app.controller.cmd.EditTutorProfileCmd;
import it.aldi.app.controller.dto.OwnerProfileDto;
import it.aldi.app.controller.dto.StudentProfileDto;
import it.aldi.app.controller.dto.TutorProfileDto;
import it.aldi.app.domain.BimbelUser;
import it.aldi.app.domain.Owner;
import it.aldi.app.domain.Student;
import it.aldi.app.domain.Tutor;
import it.aldi.app.service.domain.*;
import it.aldi.app.service.profile.ProfileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ProfileServiceImpl implements ProfileService {

    private final OwnerService ownerService;

    private final StudentService studentService;

    private final TutorService tutorService;

    private final BimbelUserService bimbelUserService;

    private final BimbelUserDetailsService bimbelUserDetailsService;

    private final OrganizationService organizationService;

    public ProfileServiceImpl(OwnerService ownerService,
                              StudentService studentService,
                              TutorService tutorService, BimbelUserService bimbelUserService,
                              BimbelUserDetailsService bimbelUserDetailsService,
                              OrganizationService organizationService) {
        this.ownerService = ownerService;
        this.studentService = studentService;
        this.tutorService = tutorService;
        this.bimbelUserService = bimbelUserService;
        this.bimbelUserDetailsService = bimbelUserDetailsService;
        this.organizationService = organizationService;
    }

    @Override
    public OwnerProfileDto getOwnerProfile(BimbelUser bimbelUser) {
        Owner owner = ownerService.findByUserId(bimbelUser.getId())
            .orElseThrow(() -> new IllegalArgumentException("Owner not found for user: " + bimbelUser));

        return OwnerProfileDto.from(owner);
    }

    @Override
    public StudentProfileDto getStudentProfile(BimbelUser bimbelUser) {
        Student student = studentService.findByUserId(bimbelUser.getId())
            .orElseThrow(() -> new IllegalArgumentException("Student not found for user: " + bimbelUser));

        return StudentProfileDto.from(student);
    }

    @Override
    public TutorProfileDto getTutorProfile(BimbelUser bimbelUser) {
        Tutor tutor = tutorService.findByUserId(bimbelUser.getId())
            .orElseThrow(() -> new IllegalArgumentException("Tutor not found for user: " + bimbelUser));

        return TutorProfileDto.from(tutor);
    }

    @Override
    public void editOwnerProfile(EditOwnerProfileCmd editOwnerProfileCmd, BimbelUser bimbelUser) {
        Owner owner = ownerService.findByUserId(bimbelUser.getId())
            .orElseThrow(() -> new IllegalArgumentException("Owner not found for user: " + bimbelUser));

        organizationService.save(owner.getChairman().getOrganization()
            .name(editOwnerProfileCmd.getOrgName()));

        bimbelUserDetailsService.save(owner.getBimbelUser().getBimbelUserDetails()
            .phone(editOwnerProfileCmd.getPhone())
            .address(editOwnerProfileCmd.getAddress()));
    }

    @Override
    public void editStudentProfile(EditStudentProfileCmd editStudentProfileCmd, BimbelUser bimbelUser) {
        Student student = studentService.findByUserId(bimbelUser.getId())
            .orElseThrow(() -> new IllegalArgumentException("Student not found for user: " + bimbelUser));

        bimbelUserService.save(bimbelUser.name(editStudentProfileCmd.getName()));

        bimbelUserDetailsService.save(student.getBimbelUser().getBimbelUserDetails()
            .phone(editStudentProfileCmd.getPhone())
            .address(editStudentProfileCmd.getAddress()));
    }

    @Override
    public void editTutorProfile(EditTutorProfileCmd editTutorProfileCmd, BimbelUser bimbelUser) {
        Tutor tutor = tutorService.findByUserId(bimbelUser.getId())
            .orElseThrow(() -> new IllegalArgumentException("Tutor not found for user: " + bimbelUser));

        organizationService.save(tutor.getChairman().getOrganization()
            .name(editTutorProfileCmd.getOrgName()));

        bimbelUserDetailsService.save(tutor.getBimbelUser().getBimbelUserDetails()
            .phone(editTutorProfileCmd.getPhone())
            .address(editTutorProfileCmd.getAddress()));
    }
}
