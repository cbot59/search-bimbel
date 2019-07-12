package it.aldi.app.repository;

import it.aldi.app.domain.UserOrganization;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the UserOrganization entity.
 */
@SuppressWarnings("unused")
@Repository
public interface UserOrganizationRepository extends JpaRepository<UserOrganization, Long> {

}
