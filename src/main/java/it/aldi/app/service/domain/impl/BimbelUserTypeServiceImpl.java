package it.aldi.app.service.domain.impl;

import it.aldi.app.domain.BimbelUserType;
import it.aldi.app.repository.BimbelUserTypeRepository;
import it.aldi.app.service.domain.BimbelUserTypeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service Implementation for managing BimbelUserType.
 */
@Service
@Transactional
public class BimbelUserTypeServiceImpl implements BimbelUserTypeService {

    private final Logger log = LoggerFactory.getLogger(BimbelUserTypeServiceImpl.class);

    private final BimbelUserTypeRepository bimbelUserTypeRepository;

    public BimbelUserTypeServiceImpl(BimbelUserTypeRepository bimbelUserTypeRepository) {
        this.bimbelUserTypeRepository = bimbelUserTypeRepository;
    }

    /**
     * Save a bimbelUserType.
     *
     * @param bimbelUserType the entity to save
     * @return the persisted entity
     */
    @Override
    public BimbelUserType save(BimbelUserType bimbelUserType) {
        log.debug("Request to save BimbelUserType : {}", bimbelUserType);
        return bimbelUserTypeRepository.save(bimbelUserType);
    }

    /**
     * Get all the bimbelUserTypes.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<BimbelUserType> findAll() {
        log.debug("Request to get all BimbelUserTypes");
        return bimbelUserTypeRepository.findAll();
    }

    /**
     * Get one bimbelUserType by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public BimbelUserType findOne(Long id) {
        log.debug("Request to get BimbelUserType : {}", id);
        return bimbelUserTypeRepository.findById(id)
            .orElse(null);
    }

    /**
     * Delete the bimbelUserType by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete BimbelUserType : {}", id);
        bimbelUserTypeRepository.delete(id);
    }

    @Override
    public BimbelUserType findOne(String name) {
        log.debug("Request to get BimbelUserType : {}", name);
        return bimbelUserTypeRepository.findByName(name)
            .orElse(null);
    }
}
