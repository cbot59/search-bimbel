package it.aldi.app.repository;

import it.aldi.app.domain.DayOfWeek;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the DayOfWeek entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DayOfWeekRepository extends JpaRepository<DayOfWeek, Long> {

}
