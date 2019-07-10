package it.aldi.app.service.domain.impl;

import it.aldi.app.service.BimbelUserTypeService;
import it.aldi.app.domain.BimbelUserType;
import it.aldi.app.repository.BimbelUserTypeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

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
     *  get all the bimbelUserTypes where BimbelUser is null.
     *  @return the list of entities
     */
    @Transactional(readOnly = true)
    public List<BimbelUserType> findAllWhereBimbelUserIsNull() {
        log.debug("Request to get all bimbelUserTypes where BimbelUser is null");
        return StreamSupport
            .stream(bimbelUserTypeRepository.findAll().spliterator(), false)
            .filter(bimbelUserType -> bimbelUserType.getBimbelUser() == null)
            .collect(Collectors.toList());
    }

    /**
     * Get one bimbelUserType by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<BimbelUserType> findOne(Long id) {
        log.debug("Request to get BimbelUserType : {}", id);
        return bimbelUserTypeRepository.findById(id);
    }

    /**
     * Delete the bimbelUserType by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete BimbelUserType : {}", id);        bimbelUserTypeRepository.deleteById(id);
    }
}
