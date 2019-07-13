package it.aldi.app.repository;

import it.aldi.app.domain.Owner;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Spring Data  repository for the Owner entity.
 */
@SuppressWarnings("unused")
@Repository
public interface OwnerRepository extends JpaRepository<Owner, Long> {

    Optional<Owner> findById(Long id);

    Optional<Owner> findByBimbelUserId(Long id);
}
