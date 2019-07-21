package it.aldi.app.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A Owner.
 */
@Entity
@Table(name = "owner")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Owner implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private Long id;

    @OneToOne
    @JoinColumn(unique = true)
    private BimbelUser bimbelUser;

    @OneToOne
    @MapsId
    @JoinColumn(name = "id")
    private Chairman chairman;

    public Owner() {
        bimbelUser = null;
    }

    private Owner(BimbelUser bimbelUser) {
        this.bimbelUser = bimbelUser;
    }

    public static Owner initialize(BimbelUser bimbelUser) {
        return new Owner(bimbelUser);
    }

    public Chairman getChairman() {
        return chairman;
    }

    public Owner chairman(Chairman chairman) {
        this.chairman = chairman;
        return this;
    }

    public void setChairman(Chairman chairman) {
        this.chairman = chairman;
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

    public Owner bimbelUser(BimbelUser bimbelUser) {
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

        Owner owner = (Owner) o;

        return owner.id != null && id != null && Objects.equals(id, owner.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Owner{" +
            "id=" + id +
            "}";
    }
}
