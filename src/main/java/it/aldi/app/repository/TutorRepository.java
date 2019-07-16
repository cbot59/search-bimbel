package it.aldi.app.repository;

import it.aldi.app.domain.Tutor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Spring Data  repository for the Tutor entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TutorRepository extends JpaRepository<Tutor, Long> {

    Optional<Tutor> findById(Long id);

    Optional<Tutor> findByBimbelUserId(Long bimbelUserId);
}
