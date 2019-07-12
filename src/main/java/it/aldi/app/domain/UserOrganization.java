package it.aldi.app.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A UserOrganization.
 */
@Entity
@Table(name = "user_organization")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class UserOrganization implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @ManyToOne
    @JsonIgnoreProperties("userOrganizations")
    private Student student;

    @ManyToOne
    @JsonIgnoreProperties("userOrganizations")
    private Tutor tutor;

    @ManyToOne
    @JsonIgnoreProperties("userOrganizations")
    private Organization organization;

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

    public UserOrganization student(Student student) {
        this.student = student;
        return this;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Tutor getTutor() {
        return tutor;
    }

    public UserOrganization tutor(Tutor tutor) {
        this.tutor = tutor;
        return this;
    }

    public void setTutor(Tutor tutor) {
        this.tutor = tutor;
    }

    public Organization getOrganization() {
        return organization;
    }

    public UserOrganization organization(Organization organization) {
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
        UserOrganization userOrganization = (UserOrganization) o;
        if (userOrganization.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), userOrganization.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "UserOrganization{" +
            "id=" + getId() +
            "}";
    }
}
