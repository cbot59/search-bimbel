package it.aldi.app.service.impl;

import it.aldi.app.service.StudentEnrollmentService;
import it.aldi.app.domain.StudentEnrollment;
import it.aldi.app.repository.StudentEnrollmentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing StudentEnrollment.
 */
@Service
@Transactional
public class StudentEnrollmentServiceImpl implements StudentEnrollmentService {

    private final Logger log = LoggerFactory.getLogger(StudentEnrollmentServiceImpl.class);

    private final StudentEnrollmentRepository studentEnrollmentRepository;

    public StudentEnrollmentServiceImpl(StudentEnrollmentRepository studentEnrollmentRepository) {
        this.studentEnrollmentRepository = studentEnrollmentRepository;
    }

    /**
     * Save a studentEnrollment.
     *
     * @param studentEnrollment the entity to save
     * @return the persisted entity
     */
    @Override
    public StudentEnrollment save(StudentEnrollment studentEnrollment) {
        log.debug("Request to save StudentEnrollment : {}", studentEnrollment);
        return studentEnrollmentRepository.save(studentEnrollment);
    }

    /**
     * Get all the studentEnrollments.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<StudentEnrollment> findAll() {
        log.debug("Request to get all StudentEnrollments");
        return studentEnrollmentRepository.findAll();
    }


    /**
     * Get one studentEnrollment by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<StudentEnrollment> findOne(Long id) {
        log.debug("Request to get StudentEnrollment : {}", id);
        return studentEnrollmentRepository.findById(id);
    }

    /**
     * Delete the studentEnrollment by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete StudentEnrollment : {}", id);        studentEnrollmentRepository.deleteById(id);
    }
}
