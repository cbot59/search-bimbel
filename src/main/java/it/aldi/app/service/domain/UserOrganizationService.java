package it.aldi.app.service;

import it.aldi.app.domain.UserOrganization;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing UserOrganization.
 */
public interface UserOrganizationService {

    /**
     * Save a userOrganization.
     *
     * @param userOrganization the entity to save
     * @return the persisted entity
     */
    UserOrganization save(UserOrganization userOrganization);

    /**
     * Get all the userOrganizations.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<UserOrganization> findAll(Pageable pageable);


    /**
     * Get the "id" userOrganization.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<UserOrganization> findOne(Long id);

    /**
     * Delete the "id" userOrganization.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
