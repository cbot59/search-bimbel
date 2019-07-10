package it.aldi.app.service.domain.impl;

import it.aldi.app.service.BimbelUserTypeRoleService;
import it.aldi.app.domain.BimbelUserTypeRole;
import it.aldi.app.repository.BimbelUserTypeRoleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing BimbelUserTypeRole.
 */
@Service
@Transactional
public class BimbelUserTypeRoleServiceImpl implements BimbelUserTypeRoleService {

    private final Logger log = LoggerFactory.getLogger(BimbelUserTypeRoleServiceImpl.class);

    private final BimbelUserTypeRoleRepository bimbelUserTypeRoleRepository;

    public BimbelUserTypeRoleServiceImpl(BimbelUserTypeRoleRepository bimbelUserTypeRoleRepository) {
        this.bimbelUserTypeRoleRepository = bimbelUserTypeRoleRepository;
    }

    /**
     * Save a bimbelUserTypeRole.
     *
     * @param bimbelUserTypeRole the entity to save
     * @return the persisted entity
     */
    @Override
    public BimbelUserTypeRole save(BimbelUserTypeRole bimbelUserTypeRole) {
        log.debug("Request to save BimbelUserTypeRole : {}", bimbelUserTypeRole);
        return bimbelUserTypeRoleRepository.save(bimbelUserTypeRole);
    }

    /**
     * Get all the bimbelUserTypeRoles.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<BimbelUserTypeRole> findAll() {
        log.debug("Request to get all BimbelUserTypeRoles");
        return bimbelUserTypeRoleRepository.findAll();
    }


    /**
     * Get one bimbelUserTypeRole by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<BimbelUserTypeRole> findOne(Long id) {
        log.debug("Request to get BimbelUserTypeRole : {}", id);
        return bimbelUserTypeRoleRepository.findById(id);
    }

    /**
     * Delete the bimbelUserTypeRole by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete BimbelUserTypeRole : {}", id);        bimbelUserTypeRoleRepository.deleteById(id);
    }
}
