package it.aldi.app.service.domain.impl;

import it.aldi.app.domain.SubjectType;
import it.aldi.app.repository.SubjectTypeRepository;
import it.aldi.app.service.domain.SubjectTypeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service Implementation for managing SubjectType.
 */
@Service
@Transactional
public class SubjectTypeServiceImpl implements SubjectTypeService {

    private final Logger log = LoggerFactory.getLogger(SubjectTypeServiceImpl.class);

    private final SubjectTypeRepository subjectTypeRepository;

    public SubjectTypeServiceImpl(SubjectTypeRepository subjectTypeRepository) {
        this.subjectTypeRepository = subjectTypeRepository;
    }

    /**
     * Save a subjectType.
     *
     * @param subjectType the entity to save
     * @return the persisted entity
     */
    @Override
    public SubjectType save(SubjectType subjectType) {
        log.debug("Request to save SubjectType : {}", subjectType);
        return subjectTypeRepository.save(subjectType);
    }

    /**
     * Get all the subjectTypes.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<SubjectType> findAll() {
        log.debug("Request to get all SubjectTypes");
        return subjectTypeRepository.findAll();
    }

    /**
     * Get one subjectType by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public SubjectType findOne(Long id) {
        log.debug("Request to get SubjectType : {}", id);
        return subjectTypeRepository.findById(id)
            .orElse(null);
    }

    /**
     * Delete the subjectType by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete SubjectType : {}", id);
        subjectTypeRepository.delete(id);
    }
}
