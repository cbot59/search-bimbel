package it.aldi.app.repository;

import it.aldi.app.domain.BimbelUserTypeRole;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Spring Data  repository for the BimbelUserTypeRole entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BimbelUserTypeRoleRepository extends JpaRepository<BimbelUserTypeRole, Long> {

    Optional<BimbelUserTypeRole> findById(Long id);
}
