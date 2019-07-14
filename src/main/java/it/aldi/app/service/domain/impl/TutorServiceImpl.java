package it.aldi.app.service.domain.impl;

import it.aldi.app.domain.Tutor;
import it.aldi.app.repository.TutorRepository;
import it.aldi.app.service.domain.TutorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing Tutor.
 */
@Service
@Transactional
public class TutorServiceImpl implements TutorService {

    private final Logger log = LoggerFactory.getLogger(TutorServiceImpl.class);

    private final TutorRepository tutorRepository;

    public TutorServiceImpl(TutorRepository tutorRepository) {
        this.tutorRepository = tutorRepository;
    }

    /**
     * Save a tutor.
     *
     * @param tutor the entity to save
     * @return the persisted entity
     */
    @Override
    public Tutor save(Tutor tutor) {
        log.debug("Request to save Tutor : {}", tutor);
        return tutorRepository.save(tutor);
    }

    /**
     * Get all the tutors.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Tutor> findAll(Pageable pageable) {
        log.debug("Request to get all Tutors");
        return tutorRepository.findAll(pageable);
    }

    /**
     * Get one tutor by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Tutor findOne(Long id) {
        log.debug("Request to get Tutor : {}", id);
        return tutorRepository.findById(id)
            .orElse(null);
    }

    /**
     * Delete the tutor by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Tutor : {}", id);
        tutorRepository.delete(id);
    }

    @Override
    public Optional<Tutor> findByUserId(Long bimbelUserId) {
        log.debug("Request to get Tutor : {}", bimbelUserId);
        return tutorRepository.findById(bimbelUserId);
    }
}
