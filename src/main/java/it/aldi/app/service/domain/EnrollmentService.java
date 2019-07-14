package it.aldi.app.service.domain;

import it.aldi.app.domain.Enrollment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing Enrollment.
 */
public interface EnrollmentService {

    /**
     * Save a enrollment.
     *
     * @param enrollment the entity to save
     * @return the persisted entity
     */
    Enrollment save(Enrollment enrollment);

    /**
     * Get all the enrollments.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<Enrollment> findAll(Pageable pageable);

    /**
     * Get the "id" enrollment.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Enrollment findOne(Long id);

    /**
     * Delete the "id" enrollment.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
