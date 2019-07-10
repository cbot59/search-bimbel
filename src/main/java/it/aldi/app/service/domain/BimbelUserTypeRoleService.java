package it.aldi.app.service.domain;

import it.aldi.app.domain.BimbelUserTypeRole;

import java.util.List;

/**
 * Service Interface for managing BimbelUserTypeRole.
 */
public interface BimbelUserTypeRoleService {

    /**
     * Save a bimbelUserTypeRole.
     *
     * @param bimbelUserTypeRole the entity to save
     * @return the persisted entity
     */
    BimbelUserTypeRole save(BimbelUserTypeRole bimbelUserTypeRole);

    /**
     * Get all the bimbelUserTypeRoles.
     *
     * @return the list of entities
     */
    List<BimbelUserTypeRole> findAll();

    /**
     * Get the "id" bimbelUserTypeRole.
     *
     * @param id the id of the entity
     * @return the entity
     */
    BimbelUserTypeRole findOne(Long id);

    /**
     * Delete the "id" bimbelUserTypeRole.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
