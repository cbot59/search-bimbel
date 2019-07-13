package it.aldi.app.service.domain.impl;

import it.aldi.app.domain.UserOrganization;
import it.aldi.app.repository.UserOrganizationRepository;
import it.aldi.app.service.domain.UserOrganizationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service Implementation for managing UserOrganization.
 */
@Service
@Transactional
public class UserOrganizationServiceImpl implements UserOrganizationService {

    private final Logger log = LoggerFactory.getLogger(UserOrganizationServiceImpl.class);

    private final UserOrganizationRepository userOrganizationRepository;

    public UserOrganizationServiceImpl(UserOrganizationRepository userOrganizationRepository) {
        this.userOrganizationRepository = userOrganizationRepository;
    }

    /**
     * Save a userOrganization.
     *
     * @param userOrganization the entity to save
     * @return the persisted entity
     */
    @Override
    public UserOrganization save(UserOrganization userOrganization) {
        log.debug("Request to save UserOrganization : {}", userOrganization);
        return userOrganizationRepository.save(userOrganization);
    }

    /**
     * Get all the userOrganizations.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<UserOrganization> findAll(Pageable pageable) {
        log.debug("Request to get all UserOrganizations");
        return userOrganizationRepository.findAll(pageable);
    }

    /**
     * Get one userOrganization by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public UserOrganization findOne(Long id) {
        log.debug("Request to get UserOrganization : {}", id);
        return userOrganizationRepository.findById(id)
            .orElse(null);
    }

    /**
     * Delete the userOrganization by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete UserOrganization : {}", id);
        userOrganizationRepository.delete(id);
    }

    @Override
    public List<UserOrganization> findByOrganization(Long organizationId) {
        log.debug("Request to get all UserOrganizations by organizationId, {}", organizationId);
        return userOrganizationRepository.findByOrganizationId(organizationId);
    }
}
