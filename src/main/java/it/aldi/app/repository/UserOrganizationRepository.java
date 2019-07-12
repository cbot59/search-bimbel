package it.aldi.app.repository;

import it.aldi.app.domain.UserOrganization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the UserOrganization entity.
 */
@SuppressWarnings("unused")
@Repository
public interface UserOrganizationRepository extends JpaRepository<UserOrganization, Long> {

    Optional<UserOrganization> findById(Long id);

    List<UserOrganization> findByOrganization(Long organizationId);
}
