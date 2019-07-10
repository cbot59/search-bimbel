package it.aldi.app.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Enrollment.
 */
@Entity
@Table(name = "enrollment")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Enrollment implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Min(value = 1)
    @Column(name = "num_of_meeting", nullable = false)
    private Integer numOfMeeting;

    @NotNull
    @Column(name = "phone", nullable = false)
    private String phone;

    @OneToMany(mappedBy = "enrollment")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<StudentEnrollment> studentEnrollments = new HashSet<>();
    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNumOfMeeting() {
        return numOfMeeting;
    }

    public Enrollment numOfMeeting(Integer numOfMeeting) {
        this.numOfMeeting = numOfMeeting;
        return this;
    }

    public void setNumOfMeeting(Integer numOfMeeting) {
        this.numOfMeeting = numOfMeeting;
    }

    public String getPhone() {
        return phone;
    }

    public Enrollment phone(String phone) {
        this.phone = phone;
        return this;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Set<StudentEnrollment> getStudentEnrollments() {
        return studentEnrollments;
    }

    public Enrollment studentEnrollments(Set<StudentEnrollment> studentEnrollments) {
        this.studentEnrollments = studentEnrollments;
        return this;
    }

    public Enrollment addStudentEnrollment(StudentEnrollment studentEnrollment) {
        this.studentEnrollments.add(studentEnrollment);
        studentEnrollment.setEnrollment(this);
        return this;
    }

    public Enrollment removeStudentEnrollment(StudentEnrollment studentEnrollment) {
        this.studentEnrollments.remove(studentEnrollment);
        studentEnrollment.setEnrollment(null);
        return this;
    }

    public void setStudentEnrollments(Set<StudentEnrollment> studentEnrollments) {
        this.studentEnrollments = studentEnrollments;
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
        Enrollment enrollment = (Enrollment) o;
        if (enrollment.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), enrollment.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Enrollment{" +
            "id=" + getId() +
            ", numOfMeeting=" + getNumOfMeeting() +
            ", phone='" + getPhone() + "'" +
            "}";
    }
}
