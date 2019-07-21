package it.aldi.app.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Min(1)
    @Column(name = "num_of_meeting", nullable = false)
    private Integer numOfMeeting;

    @NotNull
    @Column(name = "phone", nullable = false)
    private String phone;

    @ManyToOne
    @JsonIgnoreProperties("enrollments")
    private Student student;

    @ManyToOne
    @JsonIgnoreProperties("enrollments")
    private Organization organization;

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

    public Student getStudent() {
        return student;
    }

    public Enrollment student(Student student) {
        this.student = student;
        return this;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Organization getOrganization() {
        return organization;
    }

    public Enrollment organization(Organization organization) {
        this.organization = organization;
        return this;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
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

        return enrollment.id != null && id != null && Objects.equals(id, enrollment.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Enrollment{" +
            "id=" + id +
            ", numOfMeeting=" + numOfMeeting +
            ", phone='" + phone + "'" +
            "}";
    }
}
