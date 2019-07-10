package it.aldi.app.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Student.
 */
@Entity
@Table(name = "student")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Student implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @OneToMany(mappedBy = "student")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<StudentEnrollment> studentEnrollments = new HashSet<>();
    @OneToOne
    @JoinColumn(unique = true)
    private BimbelUser bimbelUser;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<StudentEnrollment> getStudentEnrollments() {
        return studentEnrollments;
    }

    public Student studentEnrollments(Set<StudentEnrollment> studentEnrollments) {
        this.studentEnrollments = studentEnrollments;
        return this;
    }

    public Student addStudentEnrollment(StudentEnrollment studentEnrollment) {
        this.studentEnrollments.add(studentEnrollment);
        studentEnrollment.setStudent(this);
        return this;
    }

    public Student removeStudentEnrollment(StudentEnrollment studentEnrollment) {
        this.studentEnrollments.remove(studentEnrollment);
        studentEnrollment.setStudent(null);
        return this;
    }

    public void setStudentEnrollments(Set<StudentEnrollment> studentEnrollments) {
        this.studentEnrollments = studentEnrollments;
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
        if (student.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), student.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Student{" +
            "id=" + getId() +
            "}";
    }
}
