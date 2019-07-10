package it.aldi.app.service.user_management.impl;

import it.aldi.app.controller.rest.dto.response.StudentDto;
import it.aldi.app.controller.rest.dto.response.TutorDto;
import it.aldi.app.domain.BimbelUser;
import it.aldi.app.service.domain.BimbelUserService;
import it.aldi.app.service.user_management.UserManagementService;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserManagementServiceImpl implements UserManagementService {

    private final BimbelUserService bimbelUserService;

    public UserManagementServiceImpl(BimbelUserService bimbelUserService) {
        this.bimbelUserService = bimbelUserService;
    }

    @Override
    public List<TutorDto> getTutors(Long organizationId) {
        // TODO: adjust tutors api
        List<BimbelUser> bimbelUsers = Collections.emptyList();

        return bimbelUsers.stream()
            .map(TutorDto::valueOf)
            .collect(Collectors.toList());
    }

    @Override
    public List<StudentDto> getStudents(Long organizationId) {
        // TODO: adjust students api
        List<BimbelUser> bimbelUsers = Collections.emptyList();

        return bimbelUsers.stream()
            .map(StudentDto::valueOf)
            .collect(Collectors.toList());
    }
}
