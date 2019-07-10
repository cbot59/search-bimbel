package it.aldi.app.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A StudentEnrollment.
 */
@Entity
@Table(name = "student_enrollment")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class StudentEnrollment implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @ManyToOne
    @JsonIgnoreProperties("studentEnrollments")
    private Student student;

    @ManyToOne
    @JsonIgnoreProperties("studentEnrollments")
    private Enrollment enrollment;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public StudentEnrollment student(Student student) {
        this.student = student;
        return this;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Enrollment getEnrollment() {
        return enrollment;
    }

    public StudentEnrollment enrollment(Enrollment enrollment) {
        this.enrollment = enrollment;
        return this;
    }

    public void setEnrollment(Enrollment enrollment) {
        this.enrollment = enrollment;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        StudentEnrollment studentEnrollment = (StudentEnrollment) o;
        if (studentEnrollment.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), studentEnrollment.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "StudentEnrollment{" +
            "id=" + getId() +
            "}";
    }
}
