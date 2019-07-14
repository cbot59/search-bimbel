package it.aldi.app.security.model;

import it.aldi.app.domain.BimbelUser;
import it.aldi.app.util.RoleConstant;
import lombok.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Value(staticConstructor = "of")
public class BimbelUserPrincipal implements UserDetails {

    private final BimbelUser bimbelUser;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        String role = bimbelUser.getBimbelUserType().getName();

        switch (role) {
            case RoleConstant.OWNER:
            case RoleConstant.STUDENT:
            case RoleConstant.TUTOR:
                grantedAuthorities.add(new SimpleGrantedAuthority(role));
                break;
            default:
                grantedAuthorities.add(new SimpleGrantedAuthority("USER"));
        }

        return grantedAuthorities;
    }

    @Override
    public String getPassword() {
        return bimbelUser.getPassword();
    }

    @Override
    public String getUsername() {
        return bimbelUser.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
