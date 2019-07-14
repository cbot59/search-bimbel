package it.aldi.app.domain;

import it.aldi.app.controller.rest.dto.request.AddJobCmd;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

/**
 * A Job.
 */
@Entity
@Table(name = "job")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Job implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "age")
    private Integer age;

    @Column(name = "other_note")
    private String otherNote;

    @ManyToOne
    private Organization organization;

    private Job() {

    }

    public Job(AddJobCmd cmd) {
        name = cmd.getName();
        age = cmd.getAge();
        otherNote = cmd.getOtherNote();
    }

    public static Job from(AddJobCmd cmd) {
        return new Job(cmd);
    }

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

    public Job name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public Job age(Integer age) {
        this.age = age;
        return this;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getOtherNote() {
        return otherNote;
    }

    public Job otherNote(String otherNote) {
        this.otherNote = otherNote;
        return this;
    }

    public void setOtherNote(String otherNote) {
        this.otherNote = otherNote;
    }

    public Organization getOrganization() {
        return organization;
    }

    public Job organization(Organization organization) {
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

        Job job = (Job) o;

        return job.id != null && id != null && Objects.equals(id, job.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Job{" +
            "id=" + id +
            ", name='" + name + "'" +
            ", age=" + age +
            ", otherNote='" + otherNote + "'" +
            "}";
    }
}
