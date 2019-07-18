package it.aldi.app.repository;

import it.aldi.app.domain.JobApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the JobApplication entity.
 */
@SuppressWarnings("unused")
@Repository
public interface JobApplicationRepository extends JpaRepository<JobApplication, Long> {

    Optional<JobApplication> findById(Long id);

    List<JobApplication> findByOrganizationId(Long organizationId);

    @Query("select ja from JobApplication ja where ja.organization.id =:orgId and ja.approved =:approved")
    List<JobApplication> findByOrganizationAndAvailability(@Param("orgId") Long organization,
                                                           @Param("approved") Boolean approved);
}
