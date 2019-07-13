package it.aldi.app.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * A Organization.
 */
@Entity
@Table(name = "organization")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Organization implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 3)
    @Column(name = "name", nullable = false)
    private String name;

    @Size(min = 6)
    @Column(name = "address")
    private String address;

    @Size(max = 20)
    @Pattern(regexp = "^[0-9]*$")
    @Column(name = "phone", length = 20)
    private String phone;

    @Column(name = "activated")
    private Boolean activated;

    @OneToMany(mappedBy = "organization")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<UserOrganization> userOrganizations = new HashSet<>();

    @OneToOne(mappedBy = "organization")
    @JsonIgnore
    private Owner owner;

    @OneToMany(mappedBy = "organization")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Subject> subjects = new HashSet<>();

    private Organization() {
    }

    private Organization(String name) {
        this.name = name;
        address = "Jakarta";
        phone = null;
        activated = false;
    }

    public static Organization createDefault(String name) {
        return new Organization(name);
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

    public Organization name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public Organization address(String address) {
        this.address = address;
        return this;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public Organization phone(String phone) {
        this.phone = phone;
        return this;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Boolean isActivated() {
        return activated;
    }

    public Organization activated(Boolean activated) {
        this.activated = activated;
        return this;
    }

    public void setActivated(Boolean activated) {
        this.activated = activated;
    }

    public Set<UserOrganization> getUserOrganizations() {
        return Collections.unmodifiableSet(userOrganizations);
    }

    public Organization userOrganizations(Set<UserOrganization> userOrganizations) {
        this.userOrganizations = Collections.unmodifiableSet(userOrganizations);
        return this;
    }

    public Organization addUserOrganization(UserOrganization userOrganization) {
        userOrganizations.add(userOrganization);
        userOrganization.setOrganization(this);
        return this;
    }

    public Organization removeUserOrganization(UserOrganization userOrganization) {
        userOrganizations.remove(userOrganization);
        userOrganization.setOrganization(null);
        return this;
    }

    public void setUserOrganizations(Set<UserOrganization> userOrganizations) {
        this.userOrganizations = Collections.unmodifiableSet(userOrganizations);
    }

    public Owner getOwner() {
        return owner;
    }

    public Organization owner(Owner owner) {
        this.owner = owner;
        return this;
    }

    public Set<Subject> getSubjects() {
        return Collections.unmodifiableSet(subjects);
    }

    public Organization subjects(Set<Subject> subjects) {
        this.subjects = Collections.unmodifiableSet(subjects);
        return this;
    }

    public Organization addSubject(Subject subject) {
        subjects.add(subject);
        subject.setOrganization(this);
        return this;
    }

    public Organization removeSubject(Subject subject) {
        subjects.remove(subject);
        subject.setOrganization(null);
        return this;
    }

    public void setSubjects(Set<Subject> subjects) {
        this.subjects = Collections.unmodifiableSet(subjects);
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
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

        Organization organization = (Organization) o;

        return organization.id != null && id != null && Objects.equals(id, organization.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Organization{" +
            "id=" + id +
            ", name='" + name + "'" +
            ", address='" + address + "'" +
            ", phone='" + phone + "'" +
            ", activated='" + isActivated() + "'" +
            "}";
    }
}
