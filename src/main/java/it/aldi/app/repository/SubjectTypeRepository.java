package it.aldi.app.repository;

import it.aldi.app.domain.SubjectType;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Spring Data  repository for the SubjectType entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SubjectTypeRepository extends JpaRepository<SubjectType, Long> {

    Optional<SubjectType> findById(Long id);
}
