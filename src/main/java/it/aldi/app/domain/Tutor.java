package it.aldi.app.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A Tutor.
 */
@Entity
@Table(name = "tutor")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Tutor implements Serializable {

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

    public Tutor() {
    }

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

    public Chairman getChairman() {
        return chairman;
    }

    public Tutor chairman(Chairman chairman) {
        this.chairman = chairman;
        return this;
    }

    public void setChairman(Chairman chairman) {
        this.chairman = chairman;
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
