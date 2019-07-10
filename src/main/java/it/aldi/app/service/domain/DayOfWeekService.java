package it.aldi.app.service.domain;

import it.aldi.app.domain.DayOfWeek;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing DayOfWeek.
 */
public interface DayOfWeekService {

    /**
     * Save a dayOfWeek.
     *
     * @param dayOfWeek the entity to save
     * @return the persisted entity
     */
    DayOfWeek save(DayOfWeek dayOfWeek);

    /**
     * Get all the dayOfWeeks.
     *
     * @return the list of entities
     */
    List<DayOfWeek> findAll();


    /**
     * Get the "id" dayOfWeek.
     *
     * @param id the id of the entity
     * @return the entity
     */
    DayOfWeek findOne(Long id);

    /**
     * Delete the "id" dayOfWeek.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
