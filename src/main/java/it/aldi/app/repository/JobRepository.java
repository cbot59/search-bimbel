package it.aldi.app.repository;

import it.aldi.app.domain.Job;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Spring Data  repository for the Job entity.
 */
@SuppressWarnings("unused")
@Repository
public interface JobRepository extends JpaRepository<Job, Long> {

    Optional<Job> findById(Long id);

    @Query(value = "select job from Job job left join fetch job.organization " +
        "where job.organization.name like concat('%',:keyword,'%') or job.name like concat('%',:keyword,'%') ",
        countQuery = "select count(job) from Job job left join job.organization " +
            "where job.organization.name like concat('%',:keyword,'%') or job.name like concat('%',:keyword,'%') ")
    Page<Job> findAll(Pageable pageable, @Param("keyword") String search);

    Page<Job> findAllByOrganizationId(Long id, Pageable pageable);
}
