package it.aldi.app.service.domain.impl;

import it.aldi.app.domain.Role;
import it.aldi.app.repository.RoleRepository;
import it.aldi.app.service.domain.RoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing Role.
 */
@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    private final Logger log = LoggerFactory.getLogger(RoleServiceImpl.class);

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    /**
     * Save a role.
     *
     * @param role the entity to save
     * @return the persisted entity
     */
    @Override
    public Role save(Role role) {
        log.debug("Request to save Role : {}", role);
        return roleRepository.save(role);
    }

    /**
     * Get all the roles.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<Role> findAllWithEagerRelationships() {
        log.debug("Request to get all Roles");
        return roleRepository.findAllWithEagerRelationships();
    }

    /**
     * Get all the Role with eager load of many-to-many relationships.
     *
     * @return the list of entities
     */
    @Override
    public Page<Role> findAllWithEagerRelationships(Pageable pageable) {
        return roleRepository.findAllWithEagerRelationships(pageable);
    }

    /**
     * Get one role by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Role> findOne(Long id) {
        log.debug("Request to get Role : {}", id);
        return roleRepository.findOneWithEagerRelationships(id);
    }

    /**
     * Delete the role by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Role : {}", id);
        roleRepository.delete(id);
    }

    /**
     * Count all available role
     *
     * @return total number of roles
     */
    @Override
    public Long countAll() {
        return roleRepository.count();
    }

    /**
     * Get all the roles (lazy loaded).
     *
     * @return the list of entities
     */
    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    @Override
    public Role findOne(String name) {
        log.debug("Request to get Role : {}", name);
        return roleRepository.findByName(name)
            .orElse(null);
    }
}
