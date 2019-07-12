package it.aldi.app.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A BimbelUserTypeRole.
 */
@Entity
@Table(name = "bimbel_user_type_role")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class BimbelUserTypeRole implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JsonIgnoreProperties("bimbelUserTypeRoles")
    private BimbelUserType bimbelUserType;

    @ManyToOne
    @JsonIgnoreProperties("bimbelUserTypeRoles")
    private Role role;

    private BimbelUserTypeRole(BimbelUserType bimbelUserType, Role role) {
        this.bimbelUserType = bimbelUserType;
        this.role = role;
    }
    public static BimbelUserTypeRole from(BimbelUserType bimbelUserType, Role role) {
        return new BimbelUserTypeRole(bimbelUserType, role);
    }

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BimbelUserType getBimbelUserType() {
        return bimbelUserType;
    }

    public BimbelUserTypeRole bimbelUserType(BimbelUserType bimbelUserType) {
        this.bimbelUserType = bimbelUserType;
        return this;
    }

    public void setBimbelUserType(BimbelUserType bimbelUserType) {
        this.bimbelUserType = bimbelUserType;
    }

    public Role getRole() {
        return role;
    }

    public BimbelUserTypeRole role(Role role) {
        this.role = role;
        return this;
    }

    public void setRole(Role role) {
        this.role = role;
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
        BimbelUserTypeRole bimbelUserTypeRole = (BimbelUserTypeRole) o;
        return bimbelUserTypeRole.id != null && id != null && Objects.equals(id, bimbelUserTypeRole.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "BimbelUserTypeRole{" +
            "id=" + id +
            "}";
    }
}
