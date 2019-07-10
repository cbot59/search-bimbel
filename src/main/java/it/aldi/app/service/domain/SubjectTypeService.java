package it.aldi.app.service.domain;

import it.aldi.app.domain.SubjectType;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing SubjectType.
 */
public interface SubjectTypeService {

    /**
     * Save a subjectType.
     *
     * @param subjectType the entity to save
     * @return the persisted entity
     */
    SubjectType save(SubjectType subjectType);

    /**
     * Get all the subjectTypes.
     *
     * @return the list of entities
     */
    List<SubjectType> findAll();


    /**
     * Get the "id" subjectType.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<SubjectType> findOne(Long id);

    /**
     * Delete the "id" subjectType.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
