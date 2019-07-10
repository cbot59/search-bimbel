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
 * A SubjectType.
 */
@Entity
@Table(name = "subject_type")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class SubjectType implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "subjectType")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Subject> subjects = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public SubjectType name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Subject> getSubjects() {
        return Collections.unmodifiableSet(subjects);
    }

    public SubjectType subjects(Set<Subject> subjects) {
        this.subjects = Collections.unmodifiableSet(subjects);
        return this;
    }

    public SubjectType addSubject(Subject subject) {
        subjects.add(subject);
        subject.setSubjectType(this);
        return this;
    }

    public SubjectType removeSubject(Subject subject) {
        subjects.remove(subject);
        subject.setSubjectType(null);
        return this;
    }

    public void setSubjects(Set<Subject> subjects) {
        this.subjects = Collections.unmodifiableSet(subjects);
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
        SubjectType subjectType = (SubjectType) o;
        return subjectType.id != null && id != null && Objects.equals(id, subjectType.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "SubjectType{" +
            "id=" + id +
            ", name='" + name + "'" +
            "}";
    }
}
