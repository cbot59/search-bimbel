package it.aldi.app.service.domain;

import it.aldi.app.domain.BimbelUser;

import java.util.List;

/**
 * Service Interface for managing BimbelUser.
 */
public interface BimbelUserService {

    /**
     * Save a bimbelUser.
     *
     * @param bimbelUser the entity to save
     * @return the persisted entity
     */
    BimbelUser save(BimbelUser bimbelUser);

    /**
     * Get all the bimbelUsers.
     *
     * @return the list of entities
     */
    List<BimbelUser> findAll();

    /**
     * Get the "id" bimbelUser.
     *
     * @param id the id of the entity
     * @return the entity
     */
    BimbelUser findOne(Long id);

    /**
     * Delete the "id" bimbelUser.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Get the bimbelUser based on id
     *
     * @param username the username of the entity
     * @return the entity
     */
    BimbelUser findByUsername(String username);

    /**
     * Get the bimbelUser based on email
     *
     * @param email the email of the entity
     * @return the entity
     */
    BimbelUser findByEmail(String email);
}
