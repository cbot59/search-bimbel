package it.aldi.app.service.domain;

import it.aldi.app.domain.BimbelUserType;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing BimbelUserType.
 */
public interface BimbelUserTypeService {

    /**
     * Save a bimbelUserType.
     *
     * @param bimbelUserType the entity to save
     * @return the persisted entity
     */
    BimbelUserType save(BimbelUserType bimbelUserType);

    /**
     * Get all the bimbelUserTypes.
     *
     * @return the list of entities
     */
    List<BimbelUserType> findAll();
    /**
     * Get all the BimbelUserTypeDTO where BimbelUser is null.
     *
     * @return the list of entities
     */
    List<BimbelUserType> findAllWhereBimbelUserIsNull();


    /**
     * Get the "id" bimbelUserType.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<BimbelUserType> findOne(Long id);

    /**
     * Delete the "id" bimbelUserType.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
