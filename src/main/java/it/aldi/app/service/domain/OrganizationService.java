package it.aldi.app.service.domain;

import it.aldi.app.domain.Organization;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Service Interface for managing Organization.
 */
public interface OrganizationService {

    /**
     * Save a organization.
     *
     * @param organization the entity to save
     * @return the persisted entity
     */
    Organization save(Organization organization);

    /**
     * Get all the organizations.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<Organization> findAll(Pageable pageable);

    /**
     * Get all the OrganizationDTO where Chairman is null.
     *
     * @return the list of entities
     */
    List<Organization> findAllWhereChairmanIsNull();

    /**
     * Get the "id" organization.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Organization findOne(Long id);

    /**
     * Delete the "id" organization.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
