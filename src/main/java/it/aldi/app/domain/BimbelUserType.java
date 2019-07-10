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
 * A BimbelUserType.
 */
@Entity
@Table(name = "bimbel_user_type")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class BimbelUserType implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "bimbelUserType")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<BimbelUserTypeRole> bimbelUserTypeRoles = new HashSet<>();
    @OneToOne(mappedBy = "bimbelUserType")
    @JsonIgnore
    private BimbelUser bimbelUser;

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

    public BimbelUserType name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<BimbelUserTypeRole> getBimbelUserTypeRoles() {
        return bimbelUserTypeRoles;
    }

    public BimbelUserType bimbelUserTypeRoles(Set<BimbelUserTypeRole> bimbelUserTypeRoles) {
        this.bimbelUserTypeRoles = bimbelUserTypeRoles;
        return this;
    }

    public BimbelUserType addBimbelUserTypeRole(BimbelUserTypeRole bimbelUserTypeRole) {
        this.bimbelUserTypeRoles.add(bimbelUserTypeRole);
        bimbelUserTypeRole.setBimbelUserType(this);
        return this;
    }

    public BimbelUserType removeBimbelUserTypeRole(BimbelUserTypeRole bimbelUserTypeRole) {
        this.bimbelUserTypeRoles.remove(bimbelUserTypeRole);
        bimbelUserTypeRole.setBimbelUserType(null);
        return this;
    }

    public void setBimbelUserTypeRoles(Set<BimbelUserTypeRole> bimbelUserTypeRoles) {
        this.bimbelUserTypeRoles = bimbelUserTypeRoles;
    }

    public BimbelUser getBimbelUser() {
        return bimbelUser;
    }

    public BimbelUserType bimbelUser(BimbelUser bimbelUser) {
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
        BimbelUserType bimbelUserType = (BimbelUserType) o;
        if (bimbelUserType.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), bimbelUserType.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "BimbelUserType{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            "}";
    }
}
