package it.aldi.app.repository;

import it.aldi.app.domain.Chairman;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Spring Data  repository for the Chairman entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ChairmanRepository extends JpaRepository<Chairman, Long> {
    Optional<Chairman> findById(Long id);
}
