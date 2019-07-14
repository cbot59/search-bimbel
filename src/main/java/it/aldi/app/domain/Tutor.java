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
 * A Tutor.
 */
@Entity
@Table(name = "tutor")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Tutor implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "tutor")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<UserOrganization> userOrganizations = new HashSet<>();

    @OneToOne
    @JoinColumn(unique = true)
    private BimbelUser bimbelUser;

    public Tutor(BimbelUser bimbelUser) {
        this.bimbelUser = bimbelUser;
    }

    public static Tutor initialize(BimbelUser bimbelUser) {
        return new Tutor(bimbelUser);
    }

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<UserOrganization> getUserOrganizations() {
        return Collections.unmodifiableSet(userOrganizations);
    }

    public Tutor userOrganizations(Set<UserOrganization> userOrganizations) {
        this.userOrganizations = Collections.unmodifiableSet(userOrganizations);
        return this;
    }

    public Tutor addUserOrganization(UserOrganization userOrganization) {
        this.userOrganizations.add(userOrganization);
        userOrganization.setTutor(this);
        return this;
    }

    public Tutor removeUserOrganization(UserOrganization userOrganization) {
        this.userOrganizations.remove(userOrganization);
        userOrganization.setTutor(null);
        return this;
    }

    public void setUserOrganizations(Set<UserOrganization> userOrganizations) {
        this.userOrganizations = Collections.unmodifiableSet(userOrganizations);
    }

    public BimbelUser getBimbelUser() {
        return bimbelUser;
    }

    public Tutor bimbelUser(BimbelUser bimbelUser) {
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

        Tutor tutor = (Tutor) o;

        return tutor.id != null && id != null && Objects.equals(id, tutor.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Tutor{" +
            "id=" + id +
            "}";
    }
}
