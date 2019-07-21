package it.aldi.app.service.domain;

import it.aldi.app.domain.Chairman;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing Chairman.
 */
public interface ChairmanService {

    /**
     * Save a chairman.
     *
     * @param chairman the entity to save
     * @return the persisted entity
     */
    Chairman save(Chairman chairman);

    /**
     * Get all the chairmen.
     *
     * @return the list of entities
     */
    List<Chairman> findAll();

    /**
     * Get the "id" chairman.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<Chairman> findOne(Long id);

    /**
     * Delete the "id" chairman.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
