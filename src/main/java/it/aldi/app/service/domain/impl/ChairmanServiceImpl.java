package it.aldi.app.service.domain.impl;

import it.aldi.app.domain.Chairman;
import it.aldi.app.repository.ChairmanRepository;
import it.aldi.app.repository.OrganizationRepository;
import it.aldi.app.service.domain.ChairmanService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing Chairman.
 */
@Service
@Transactional
public class ChairmanServiceImpl implements ChairmanService {

    private final Logger log = LoggerFactory.getLogger(ChairmanServiceImpl.class);

    private final ChairmanRepository chairmanRepository;

    private final OrganizationRepository organizationRepository;

    public ChairmanServiceImpl(ChairmanRepository chairmanRepository, OrganizationRepository organizationRepository) {
        this.chairmanRepository = chairmanRepository;
        this.organizationRepository = organizationRepository;
    }

    /**
     * Save a chairman.
     *
     * @param chairman the entity to save
     * @return the persisted entity
     */
    @Override
    public Chairman save(Chairman chairman) {
        log.debug("Request to save Chairman : {}", chairman);
        long organizationId = chairman.getOrganization().getId();
        organizationRepository.findById(organizationId).ifPresent(chairman::organization);
        return chairmanRepository.save(chairman);
    }

    /**
     * Get all the chairmen.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<Chairman> findAll() {
        log.debug("Request to get all Chairmen");
        return chairmanRepository.findAll();
    }

    /**
     * Get one chairman by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Chairman> findOne(Long id) {
        log.debug("Request to get Chairman : {}", id);
        return chairmanRepository.findById(id);
    }

    /**
     * Delete the chairman by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Chairman : {}", id);
        chairmanRepository.delete(id);
    }
}
