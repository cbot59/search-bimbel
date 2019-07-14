package it.aldi.app.repository;

import it.aldi.app.domain.BimbelUserType;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Spring Data  repository for the BimbelUserType entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BimbelUserTypeRepository extends JpaRepository<BimbelUserType, Long> {

    Optional<BimbelUserType> findById(Long id);

    Optional<BimbelUserType> findByName(String name);
}
