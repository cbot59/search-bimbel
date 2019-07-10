package it.aldi.app.service.domain.impl;

import it.aldi.app.service.DayOfWeekService;
import it.aldi.app.domain.DayOfWeek;
import it.aldi.app.repository.DayOfWeekRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing DayOfWeek.
 */
@Service
@Transactional
public class DayOfWeekServiceImpl implements DayOfWeekService {

    private final Logger log = LoggerFactory.getLogger(DayOfWeekServiceImpl.class);

    private final DayOfWeekRepository dayOfWeekRepository;

    public DayOfWeekServiceImpl(DayOfWeekRepository dayOfWeekRepository) {
        this.dayOfWeekRepository = dayOfWeekRepository;
    }

    /**
     * Save a dayOfWeek.
     *
     * @param dayOfWeek the entity to save
     * @return the persisted entity
     */
    @Override
    public DayOfWeek save(DayOfWeek dayOfWeek) {
        log.debug("Request to save DayOfWeek : {}", dayOfWeek);
        return dayOfWeekRepository.save(dayOfWeek);
    }

    /**
     * Get all the dayOfWeeks.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<DayOfWeek> findAll() {
        log.debug("Request to get all DayOfWeeks");
        return dayOfWeekRepository.findAll();
    }


    /**
     * Get one dayOfWeek by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<DayOfWeek> findOne(Long id) {
        log.debug("Request to get DayOfWeek : {}", id);
        return dayOfWeekRepository.findById(id);
    }

    /**
     * Delete the dayOfWeek by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete DayOfWeek : {}", id);        dayOfWeekRepository.deleteById(id);
    }
}
