package it.aldi.app.service.domain.impl;

import it.aldi.app.domain.Organization;
import it.aldi.app.repository.OrganizationRepository;
import it.aldi.app.service.domain.OrganizationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing Organization.
 */
@Service
@Transactional
public class OrganizationServiceImpl implements OrganizationService {

    private final Logger log = LoggerFactory.getLogger(OrganizationServiceImpl.class);

    private final OrganizationRepository organizationRepository;

    public OrganizationServiceImpl(OrganizationRepository organizationRepository) {
        this.organizationRepository = organizationRepository;
    }

    /**
     * Save a organization.
     *
     * @param organization the entity to save
     * @return the persisted entity
     */
    @Override
    public Organization save(Organization organization) {
        log.debug("Request to save Organization : {}", organization);
        return organizationRepository.save(organization);
    }

    /**
     * Get all the organizations.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Organization> findAll(Pageable pageable) {
        log.debug("Request to get all Organizations");
        return organizationRepository.findAll(pageable);
    }

    /**
     * get all the organizations where Owner is null.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<Organization> findAllWhereOwnerIsNull() {
        log.debug("Request to get all organizations where Owner is null");
        return organizationRepository.findAll().stream()
            .filter(organization -> organization.getOwner() == null)
            .collect(Collectors.toList());
    }

    /**
     * Get one organization by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Organization> findOne(Long id) {
        log.debug("Request to get Organization : {}", id);
        return organizationRepository.findById(id);
    }

    /**
     * Delete the organization by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Organization : {}", id);
        organizationRepository.delete(id);
    }
}
