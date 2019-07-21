package it.aldi.app.repository;

import it.aldi.app.domain.BimbelUserDetails;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Spring Data  repository for the BimbelUserDetails entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BimbelUserDetailsRepository extends JpaRepository<BimbelUserDetails, Long> {
    Optional<BimbelUserDetails> findById(Long id);
}
