package it.aldi.app.service.domain;

import it.aldi.app.domain.Tutor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing Tutor.
 */
public interface TutorService {

    /**
     * Save a tutor.
     *
     * @param tutor the entity to save
     * @return the persisted entity
     */
    Tutor save(Tutor tutor);

    /**
     * Get all the tutors.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<Tutor> findAll(Pageable pageable);

    /**
     * Get the "id" tutor.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Tutor findOne(Long id);

    /**
     * Delete the "id" tutor.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
