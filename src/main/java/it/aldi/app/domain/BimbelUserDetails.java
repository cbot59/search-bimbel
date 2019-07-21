package it.aldi.app.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import it.aldi.app.controller.cmd.RegisterUserCmd;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Objects;

/**
 * A BimbelUserDetails.
 */
@Entity
@Table(name = "bimbel_user_details")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class BimbelUserDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 3)
    @Column(name = "phone", nullable = false)
    private String phone;

    @Column(name = "address")
    private String address;

    @OneToOne(mappedBy = "bimbelUserDetails")
    @JsonIgnore
    private BimbelUser bimbelUser;

    private BimbelUserDetails() {
    }

    private BimbelUserDetails(RegisterUserCmd cmd) {
        phone = cmd.getPhone();
        address = cmd.getAddress();
    }

    public static BimbelUserDetails from(RegisterUserCmd cmd) {
        return new BimbelUserDetails(cmd);
    }

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public BimbelUserDetails phone(String phone) {
        this.phone = phone;
        return this;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public BimbelUserDetails address(String address) {
        this.address = address;
        return this;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public BimbelUser getBimbelUser() {
        return bimbelUser;
    }

    public BimbelUserDetails bimbelUser(BimbelUser bimbelUser) {
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

        BimbelUserDetails bimbelUserDetails = (BimbelUserDetails) o;

        return bimbelUserDetails.id != null && id != null && Objects.equals(id,
            bimbelUserDetails.id
        );
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "BimbelUserDetails{" +
            "id=" + id +
            ", phone='" + phone + "'" +
            ", address='" + address + "'" +
            "}";
    }
}
