package it.aldi.app.service.domain.impl;

import it.aldi.app.domain.BimbelUserDetails;
import it.aldi.app.repository.BimbelUserDetailsRepository;
import it.aldi.app.service.domain.BimbelUserDetailsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Service Implementation for managing BimbelUserDetails.
 */
@Service
@Transactional
public class BimbelUserDetailsServiceImpl implements BimbelUserDetailsService {

    private final Logger log = LoggerFactory.getLogger(BimbelUserDetailsServiceImpl.class);

    private final BimbelUserDetailsRepository bimbelUserDetailsRepository;

    public BimbelUserDetailsServiceImpl(BimbelUserDetailsRepository bimbelUserDetailsRepository) {
        this.bimbelUserDetailsRepository = bimbelUserDetailsRepository;
    }

    /**
     * Save a bimbelUserDetails.
     *
     * @param bimbelUserDetails the entity to save
     * @return the persisted entity
     */
    @Override
    public BimbelUserDetails save(BimbelUserDetails bimbelUserDetails) {
        log.debug("Request to save BimbelUserDetails : {}", bimbelUserDetails);
        return bimbelUserDetailsRepository.save(bimbelUserDetails);
    }

    /**
     * Get all the bimbelUserDetails.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<BimbelUserDetails> findAll() {
        log.debug("Request to get all BimbelUserDetails");
        return bimbelUserDetailsRepository.findAll();
    }

    /**
     * get all the bimbelUserDetails where BimbelUser is null.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<BimbelUserDetails> findAllWhereBimbelUserIsNull() {
        log.debug("Request to get all bimbelUserDetails where BimbelUser is null");
        return StreamSupport
            .stream(bimbelUserDetailsRepository.findAll().spliterator(), false)
            .filter(bimbelUserDetails -> bimbelUserDetails.getBimbelUser() == null)
            .collect(Collectors.toList());
    }

    /**
     * Get one bimbelUserDetails by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<BimbelUserDetails> findOne(Long id) {
        log.debug("Request to get BimbelUserDetails : {}", id);
        return bimbelUserDetailsRepository.findById(id);
    }

    /**
     * Delete the bimbelUserDetails by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete BimbelUserDetails : {}", id);
        bimbelUserDetailsRepository.delete(id);
    }
}
