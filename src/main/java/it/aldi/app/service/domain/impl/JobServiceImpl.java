package it.aldi.app.service.domain.impl;

import it.aldi.app.domain.Job;
import it.aldi.app.exception.EntityNotFoundException;
import it.aldi.app.repository.JobRepository;
import it.aldi.app.service.domain.JobService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing Job.
 */
@Service
@Transactional
public class JobServiceImpl implements JobService {

    private final Logger log = LoggerFactory.getLogger(JobServiceImpl.class);

    private final JobRepository jobRepository;

    public JobServiceImpl(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    /**
     * Save a job.
     *
     * @param job the entity to save
     * @return the persisted entity
     */
    @Override
    public Job save(Job job) {
        log.debug("Request to save Job : {}", job);
        return jobRepository.save(job);
    }

    /**
     * Get all the jobs.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Job> findAll(Pageable pageable) {
        log.debug("Request to get all Jobs");
        return jobRepository.findAll(pageable);
    }

    /**
     * Get one job by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Job findOne(Long id) {
        log.debug("Request to get Job : {}", id);
        return jobRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Job not available, jobId: " + id));
    }

    /**
     * Delete the job by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Job : {}", id);
        jobRepository.delete(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Job> findAll(Pageable pageable, String search) {
        log.debug("Request to get all Jobs with a query: {}", search);
        return jobRepository.findAll(pageable, search);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Job> findAllByOrgId(Long organizationId, Pageable pageable) {
        log.debug("Request to get all Jobs from Organization: {}", organizationId);
        return jobRepository.findAllByOrganizationId(organizationId, pageable);
    }
}
