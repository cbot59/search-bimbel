package it.aldi.app;

import it.aldi.app.controller.dto.BimbelUserDto;
import it.aldi.app.controller.rest.dto.request.AddJobCmd;
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
public final class CustomApplicationRunner implements ApplicationRunner {

    private final RegisterService registerService;

    private final BimbelUserRepository bimbelUserRepository;

    private final JobManagementService jobManagementService;

    @Autowired
    private CustomApplicationRunner(RegisterService registerService, BimbelUserRepository bimbelUserRepository,
                                    JobManagementService jobManagementService) {
        this.registerService = registerService;
        this.bimbelUserRepository = bimbelUserRepository;
        this.jobManagementService = jobManagementService;
    }

    @Override
    public void run(ApplicationArguments applicationArguments) {
        if (bimbelUserRepository.count() < 1) {
            insertInitialUser();
            insertInitialJob();
        }
    }

    private void insertInitialUser() {
        List<BimbelUserDto> bimbelUserDtos = new ArrayList<>();

        bimbelUserDtos.add(BimbelUserDto.builder()
            .email("rivaldi.saputra@jurnal.id")
            .username("rivaldi.saputra")
            .password("aldi123")
            .name("Rivaldi Saputra")
            .roles("OWNER")
            .build());

        bimbelUserDtos.add(BimbelUserDto.builder()
            .email("rivaldi.saputra@jurnal.id2")
            .username("rivaldi.dua")
            .password("aldi123")
            .name("Rivaldi Dua")
            .roles("TUTOR")
            .build());

        bimbelUserDtos.add(BimbelUserDto.builder()
            .email("rivaldi.saputra@jurnal.id3")
            .username("rivaldi.tiga")
            .password("aldi123")
            .name("Rivaldi Tiga")
            .roles("STUDENT")
            .build());

        for (BimbelUserDto bimbelUserDto : bimbelUserDtos) {
            registerService.registerUser(bimbelUserDto);
        }
    }

    private void insertInitialJob() {
        List<AddJobCmd> jobs = new ArrayList<>();

        jobs.add(AddJobCmd.builder()
            .name("Guru SD Matematika")
            .age(18)
            .otherNote("Bisa mengajar perkalian")
            .organizationId(1L)
            .build());

        jobs.add(AddJobCmd.builder()
            .name("Guru SMP Bahasa Indonesia")
            .age(18)
            .otherNote("Puisi, Prosa, Paragraf")
            .organizationId(1L)
            .build());

        jobs.add(AddJobCmd.builder()
            .name("Guru SMA Kimia")
            .age(24)
            .otherNote("Hafal tabel unsur kimia")
            .organizationId(1L)
            .build());

        jobs.forEach(jobManagementService::saveJob);
    }
}
