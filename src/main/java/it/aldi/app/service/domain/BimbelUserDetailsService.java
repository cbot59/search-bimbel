package it.aldi.app.service.domain;

import it.aldi.app.domain.BimbelUserDetails;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing BimbelUserDetails.
 */
public interface BimbelUserDetailsService {

    /**
     * Save a bimbelUserDetails.
     *
     * @param bimbelUserDetails the entity to save
     * @return the persisted entity
     */
    BimbelUserDetails save(BimbelUserDetails bimbelUserDetails);

    /**
     * Get all the bimbelUserDetails.
     *
     * @return the list of entities
     */
    List<BimbelUserDetails> findAll();

    /**
     * Get all the BimbelUserDetailsDTO where BimbelUser is null.
     *
     * @return the list of entities
     */
    List<BimbelUserDetails> findAllWhereBimbelUserIsNull();

    /**
     * Get the "id" bimbelUserDetails.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<BimbelUserDetails> findOne(Long id);

    /**
     * Delete the "id" bimbelUserDetails.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
