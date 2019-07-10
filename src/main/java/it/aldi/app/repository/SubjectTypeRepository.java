package it.aldi.app.repository;

import it.aldi.app.domain.SubjectType;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the SubjectType entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SubjectTypeRepository extends JpaRepository<SubjectType, Long> {

}
