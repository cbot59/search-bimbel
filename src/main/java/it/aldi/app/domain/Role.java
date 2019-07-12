package it.aldi.app.domain;

import it.aldi.app.util.RoleConstant;
import lombok.AllArgsConstructor;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * A Role.
 */
@Entity
@AllArgsConstructor
@Table(name = "role")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Role implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "role_privilege",
        joinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "privilege_id", referencedColumnName = "id"))
    private Set<Privilege> privileges;

    @OneToMany(mappedBy = "role")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<BimbelUserTypeRole> bimbelUserTypeRoles = new HashSet<>();

    private Role() {
    }

    private Role(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    private Role(String name) {
        this.name = name;
    }

    public static Role valueOf(Long id, String name) {
        return new Role(id, name);
    }

    public static Role owner() {
        return new Role(RoleConstant.OWNER);
    }

    public static Role tutor() {
        return new Role(RoleConstant.TUTOR);
    }

    public static Role student() {
        return new Role(RoleConstant.STUDENT);
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

    public Role name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Privilege> getPrivileges() {
        return Collections.unmodifiableSet(privileges);
    }

    public Role privileges(Set<Privilege> privileges) {
        this.privileges = Collections.unmodifiableSet(privileges);
        return this;
    }

    public Role addPrivilege(Privilege privilege) {
        privileges.add(privilege);
        privilege.getRoles().add(this);
        return this;
    }

    public Role removePrivilege(Privilege privilege) {
        privileges.remove(privilege);
        privilege.getRoles().remove(this);
        return this;
    }

    public void setPrivileges(Set<Privilege> privileges) {
        this.privileges = Collections.unmodifiableSet(privileges);
    }

    public Set<BimbelUserTypeRole> getBimbelUserTypeRoles() {
        return bimbelUserTypeRoles;
    }

    public Role bimbelUserTypeRoles(Set<BimbelUserTypeRole> bimbelUserTypeRoles) {
        this.bimbelUserTypeRoles = bimbelUserTypeRoles;
        return this;
    }

    public Role addBimbelUserTypeRole(BimbelUserTypeRole bimbelUserTypeRole) {
        bimbelUserTypeRoles.add(bimbelUserTypeRole);
        bimbelUserTypeRole.setRole(this);
        return this;
    }

    public Role removeBimbelUserTypeRole(BimbelUserTypeRole bimbelUserTypeRole) {
        bimbelUserTypeRoles.remove(bimbelUserTypeRole);
        bimbelUserTypeRole.setRole(null);
        return this;
    }

    public void setBimbelUserTypeRoles(Set<BimbelUserTypeRole> bimbelUserTypeRoles) {
        this.bimbelUserTypeRoles = bimbelUserTypeRoles;
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
        Role role = (Role) o;
        return (role.id != null && id != null && Objects.equals(id, role.id))
            || (role.name != null && name != null && Objects.equals(name, role.name));
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }

    @Override
    public String toString() {
        return "Role{" +
            "id=" + id +
            ", name='" + name + "'" +
            "}";
    }
}
