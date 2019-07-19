package it.aldi.app.service.domain;

import it.aldi.app.domain.Job;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing Job.
 */
public interface JobService {

    /**
     * Save a job.
     *
     * @param job the entity to save
     * @return the persisted entity
     */
    Job save(Job job);

    /**
     * Get all the jobs.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<Job> findAll(Pageable pageable);

    /**
     * Get the "id" job.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Job findOne(Long id);

    /**
     * Delete the "id" job.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Get all the jobs within a query search
     *
     * @param pageable the pagination information
     * @param search   keyword to find job
     * @return the list of entities
     */
    Page<Job> findAll(Pageable pageable, String search);

    /**
     * Get all the jobs on an Organization
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<Job> findAllByOrgId(Long organizationId, Pageable pageable);
}
