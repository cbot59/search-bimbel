package it.aldi.app.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * A Student.
 */
@Entity
@Table(name = "student")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Student implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(unique = true)
    private BimbelUser bimbelUser;

    @OneToMany(mappedBy = "student")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<UserOrganization> userOrganizations = new HashSet<>();

    @OneToMany(mappedBy = "student")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Enrollment> enrollments = new HashSet<>();

    public Student() {
    }

    public Student(BimbelUser bimbelUser) {
        this.bimbelUser = bimbelUser;
    }

    public static Student initialize(BimbelUser bimbelUser) {
        return new Student(bimbelUser);
    }

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BimbelUser getBimbelUser() {
        return bimbelUser;
    }

    public Student bimbelUser(BimbelUser bimbelUser) {
        this.bimbelUser = bimbelUser;
        return this;
    }

    public void setBimbelUser(BimbelUser bimbelUser) {
        this.bimbelUser = bimbelUser;
    }

    public Set<UserOrganization> getUserOrganizations() {
        return Collections.unmodifiableSet(userOrganizations);
    }

    public Student userOrganizations(Set<UserOrganization> userOrganizations) {
        this.userOrganizations = Collections.unmodifiableSet(userOrganizations);
        return this;
    }

    public Student addUserOrganization(UserOrganization userOrganization) {
        userOrganizations.add(userOrganization);
        userOrganization.setStudent(this);
        return this;
    }

    public Student removeUserOrganization(UserOrganization userOrganization) {
        userOrganizations.remove(userOrganization);
        userOrganization.setStudent(null);
        return this;
    }

    public void setUserOrganizations(Set<UserOrganization> userOrganizations) {
        this.userOrganizations = Collections.unmodifiableSet(userOrganizations);
    }

    public Set<Enrollment> getEnrollments() {
        return Collections.unmodifiableSet(enrollments);
    }

    public Student enrollments(Set<Enrollment> enrollments) {
        this.enrollments = Collections.unmodifiableSet(enrollments);
        return this;
    }

    public Student addEnrollment(Enrollment enrollment) {
        enrollments.add(enrollment);
        enrollment.setStudent(this);
        return this;
    }

    public Student removeEnrollment(Enrollment enrollment) {
        enrollments.remove(enrollment);
        enrollment.setStudent(null);
        return this;
    }

    public void setEnrollments(Set<Enrollment> enrollments) {
        this.enrollments = Collections.unmodifiableSet(enrollments);
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

        Student student = (Student) o;

        return student.id != null && id != null && Objects.equals(id, student.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Student{" +
            "id=" + id +
            "}";
    }
}
