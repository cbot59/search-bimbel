package it.aldi.app.repository;

import it.aldi.app.domain.JobApplication;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Spring Data  repository for the JobApplication entity.
 */
@SuppressWarnings("unused")
@Repository
public interface JobApplicationRepository extends JpaRepository<JobApplication, Long> {

    Optional<JobApplication> findById(Long id);
}
