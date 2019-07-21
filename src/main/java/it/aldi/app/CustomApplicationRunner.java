package it.aldi.app;

import it.aldi.app.controller.cmd.RegisterUserCmd;
import it.aldi.app.repository.BimbelUserRepository;
import it.aldi.app.service.job_management.JobManagementService;
import it.aldi.app.service.register.RegisterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class CustomApplicationRunner implements ApplicationRunner {

    private final RegisterService registerService;

    private final BimbelUserRepository bimbelUserRepository;

    private final JobManagementService jobManagementService;

    @Autowired
    public CustomApplicationRunner(RegisterService registerService, BimbelUserRepository bimbelUserRepository,
                                    JobManagementService jobManagementService) {
        this.registerService = registerService;
        this.bimbelUserRepository = bimbelUserRepository;
        this.jobManagementService = jobManagementService;
    }

    @Override
    public void run(ApplicationArguments applicationArguments) {
        if (bimbelUserRepository.count() < 1) {
            insertInitialUser();
        }
    }

    private void insertInitialUser() {
        List<RegisterUserCmd> registerUserCmds = new ArrayList<>();

        registerUserCmds.add(RegisterUserCmd.builder()
            .email("rivaldi.saputra@jurnal.id")
            .username("rivaldi.saputra")
            .password("aldi123")
            .name("Rivaldi Saputra")
            .roles("OWNER")
            .phone("081234567890")
            .address("address 1")
            .build());

        registerUserCmds.add(RegisterUserCmd.builder()
            .email("rivaldi.saputra@jurnal.id2")
            .username("rivaldi.dua")
            .password("aldi123")
            .name("Rivaldi Dua")
            .roles("TUTOR")
            .phone("081234567891")
            .address("address 2")
            .build());

        registerUserCmds.add(RegisterUserCmd.builder()
            .email("rivaldi.saputra@jurnal.id3")
            .username("rivaldi.tiga")
            .password("aldi123")
            .name("Rivaldi Tiga")
            .roles("STUDENT")
            .phone("081234567892")
            .address("address 3")
            .build());

        for (RegisterUserCmd cmd : registerUserCmds) {
            registerService.registerUser(cmd);
        }
    }
}
