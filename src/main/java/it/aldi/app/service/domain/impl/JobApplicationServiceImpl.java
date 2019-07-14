package it.aldi.app.service.domain.impl;

import it.aldi.app.domain.JobApplication;
import it.aldi.app.repository.JobApplicationRepository;
import it.aldi.app.service.domain.JobApplicationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing JobApplication.
 */
@Service
@Transactional
public class JobApplicationServiceImpl implements JobApplicationService {

    private final Logger log = LoggerFactory.getLogger(JobApplicationServiceImpl.class);

    private final JobApplicationRepository jobApplicationRepository;

    public JobApplicationServiceImpl(JobApplicationRepository jobApplicationRepository) {
        this.jobApplicationRepository = jobApplicationRepository;
    }

    /**
     * Save a jobApplication.
     *
     * @param jobApplication the entity to save
     * @return the persisted entity
     */
    @Override
    public JobApplication save(JobApplication jobApplication) {
        log.debug("Request to save JobApplication : {}", jobApplication);
        return jobApplicationRepository.save(jobApplication);
    }

    /**
     * Get all the jobApplications.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<JobApplication> findAll() {
        log.debug("Request to get all JobApplications");
        return jobApplicationRepository.findAll();
    }

    /**
     * Get one jobApplication by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<JobApplication> findOne(Long id) {
        log.debug("Request to get JobApplication : {}", id);
        return jobApplicationRepository.findById(id);
    }

    /**
     * Delete the jobApplication by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete JobApplication : {}", id);
        jobApplicationRepository.delete(id);
    }
}
