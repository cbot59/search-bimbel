package it.aldi.app.domain;

import it.aldi.app.controller.dto.BimbelUserDto;
import it.aldi.app.util.RegexConstant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.experimental.Wither;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

/**
 * A BimbelUser.
 */
@Builder
@Entity
@AllArgsConstructor
@Table(name = "bimbel_user")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class BimbelUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 6)
    @Pattern(regexp = RegexConstant.USERNAME_REGEX)
    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @NotNull
    @Pattern(regexp = RegexConstant.NAME_REGEX)
    @Column(name = "name", nullable = false)
    private String name;

    @Wither
    @NotNull
    @Size(min = 6)
    @Column(name = "password", nullable = false)
    private String password;

    @NotNull
    @Pattern(regexp = RegexConstant.EMAIL_REGEX)
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @OneToOne
    @JoinColumn(unique = true)
    private BimbelUserType bimbelUserType;

    private BimbelUser() {
    }

    private BimbelUser(BimbelUserDto bimbelUserDto) {
        username = bimbelUserDto.getUsername();
        name = bimbelUserDto.getName();
        password = bimbelUserDto.getPassword();
        email = bimbelUserDto.getEmail();
    }

    public static BimbelUser from(BimbelUserDto bimbelUserDto) {
        return new BimbelUser(bimbelUserDto);
    }

    public static BimbelUser register(BimbelUserDto bimbelUserDto, Set<Role> roles) {
        // TODO: adjust registration process
        return from(bimbelUserDto);
    }

    public static BimbelUser empty() {
        return new BimbelUser();
    }

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public BimbelUser username(String username) {
        this.username = username;
        return this;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public BimbelUser name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public BimbelUser password(String password) {
        this.password = password;
        return this;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public BimbelUser email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public BimbelUserType getBimbelUserType() {
        return bimbelUserType;
    }

    public BimbelUser bimbelUserType(BimbelUserType bimbelUserType) {
        this.bimbelUserType = bimbelUserType;
        return this;
    }

    public void setBimbelUserType(BimbelUserType bimbelUserType) {
        this.bimbelUserType = bimbelUserType;
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
        BimbelUser bimbelUser = (BimbelUser) o;
        return bimbelUser.id != null && id != null && Objects.equals(id, bimbelUser.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "BimbelUser{" +
            "id=" + id +
            ", username='" + username + "'" +
            ", name='" + name + "'" +
            ", email='" + email + "'" +
            "}";
    }
}
