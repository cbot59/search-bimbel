package it.aldi.app.repository;

import it.aldi.app.domain.StudentEnrollment;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the StudentEnrollment entity.
 */
@SuppressWarnings("unused")
@Repository
public interface StudentEnrollmentRepository extends JpaRepository<StudentEnrollment, Long> {

}
