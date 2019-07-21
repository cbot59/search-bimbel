package it.aldi.app.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A JobApplication.
 */
@Entity
@Table(name = "job_application")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class JobApplication implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "approved")
    private Boolean approved;

    @ManyToOne
    @JsonIgnoreProperties("jobApplications")
    private Job job;

    @ManyToOne
    @JsonIgnoreProperties("jobApplications")
    private Tutor tutor;

    private JobApplication() {}

    private JobApplication(Job job, Tutor tutor) {
        this.job = job;
        this.tutor = tutor;
        approved = false;
    }

    public static JobApplication from(Job job, Tutor tutor) {
        return new JobApplication(job, tutor);
    }

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean isApproved() {
        return approved;
    }

    public JobApplication approved(Boolean approved) {
        this.approved = approved;
        return this;
    }

    public void setApproved(Boolean approved) {
        this.approved = approved;
    }

    public Job getJob() {
        return job;
    }

    public JobApplication job(Job job) {
        this.job = job;
        return this;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public Tutor getTutor() {
        return tutor;
    }

    public JobApplication tutor(Tutor tutor) {
        this.tutor = tutor;
        return this;
    }

    public void setTutor(Tutor tutor) {
        this.tutor = tutor;
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
        JobApplication jobApplication = (JobApplication) o;
        return jobApplication.id != null && id != null && Objects.equals(id, jobApplication.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "JobApplication{" +
            "id=" + id +
            ", approved='" + isApproved() + "'" +
            "}";
    }
}
