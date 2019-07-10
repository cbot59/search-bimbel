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
 * A DayOfWeek.
 */
@Entity
@Table(name = "day_of_week")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class DayOfWeek implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "short_name", nullable = false)
    private String shortName;

    @Column(name = "full_name")
    private String fullName;

    @OneToMany(mappedBy = "dayOfWeek")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Schedule> schedules = new HashSet<>();
    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getShortName() {
        return shortName;
    }

    public DayOfWeek shortName(String shortName) {
        this.shortName = shortName;
        return this;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getFullName() {
        return fullName;
    }

    public DayOfWeek fullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Set<Schedule> getSchedules() {
        return schedules;
    }

    public DayOfWeek schedules(Set<Schedule> schedules) {
        this.schedules = schedules;
        return this;
    }

    public DayOfWeek addSchedule(Schedule schedule) {
        this.schedules.add(schedule);
        schedule.setDayOfWeek(this);
        return this;
    }

    public DayOfWeek removeSchedule(Schedule schedule) {
        this.schedules.remove(schedule);
        schedule.setDayOfWeek(null);
        return this;
    }

    public void setSchedules(Set<Schedule> schedules) {
        this.schedules = schedules;
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
        DayOfWeek dayOfWeek = (DayOfWeek) o;
        if (dayOfWeek.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), dayOfWeek.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "DayOfWeek{" +
            "id=" + getId() +
            ", shortName='" + getShortName() + "'" +
            ", fullName='" + getFullName() + "'" +
            "}";
    }
}
