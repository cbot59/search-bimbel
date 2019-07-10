package it.aldi.app.repository;

import it.aldi.app.domain.BimbelUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the BimbelUser entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BimbelUserRepository extends JpaRepository<BimbelUser, Long> {

    Optional<BimbelUser> findById(Long id);

    Optional<BimbelUser> findByUsername(String username);

    Optional<BimbelUser> findByEmail(String email);
}
