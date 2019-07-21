package it.aldi.app.service.user_management.impl;

import it.aldi.app.controller.rest.dto.response.StudentDto;
import it.aldi.app.controller.rest.dto.response.TutorDto;
import it.aldi.app.domain.Student;
import it.aldi.app.domain.UserOrganization;
import it.aldi.app.service.domain.UserOrganizationService;
import it.aldi.app.service.user_management.UserManagementService;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserManagementServiceImpl implements UserManagementService {

    private final UserOrganizationService userOrganizationService;

    public UserManagementServiceImpl(UserOrganizationService userOrganizationService) {
        this.userOrganizationService = userOrganizationService;
    }

    @Override
    public List<TutorDto> getTutors(Long organizationId) {
        // TODO: change business model, Tutor is no longer attached to Organization
        // Create new entity Teacher and made this method getTeachers()
        return Collections.emptyList();
    }

    @Override
    public List<StudentDto> getStudents(Long organizationId) {
        List<UserOrganization> userOrganizations = userOrganizationService.findByOrganization(organizationId);
        List<Student> students = userOrganizations.stream()
            .map(UserOrganization::getStudent)
            .collect(Collectors.toList());

        return students.stream()
            .map(student -> StudentDto.valueOf(student.getBimbelUser()))
            .collect(Collectors.toList());
    }
}
