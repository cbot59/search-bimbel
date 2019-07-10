package it.aldi.app.repository;

import it.aldi.app.domain.BimbelUserType;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the BimbelUserType entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BimbelUserTypeRepository extends JpaRepository<BimbelUserType, Long> {

}
