package it.aldi.app.service.domain;

import it.aldi.app.domain.StudentEnrollment;

import java.util.List;

/**
 * Service Interface for managing StudentEnrollment.
 */
public interface StudentEnrollmentService {

    /**
     * Save a studentEnrollment.
     *
     * @param studentEnrollment the entity to save
     * @return the persisted entity
     */
    StudentEnrollment save(StudentEnrollment studentEnrollment);

    /**
     * Get all the studentEnrollments.
     *
     * @return the list of entities
     */
    List<StudentEnrollment> findAll();

    /**
     * Get the "id" studentEnrollment.
     *
     * @param id the id of the entity
     * @return the entity
     */
    StudentEnrollment findOne(Long id);

    /**
     * Delete the "id" studentEnrollment.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
