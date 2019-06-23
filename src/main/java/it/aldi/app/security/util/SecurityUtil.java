package it.aldi.app.security.util;

import it.aldi.app.controller.Routes;
import it.aldi.app.util.RoleConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;

import java.util.Set;

@Slf4j
public final class SecurityUtil {

    private SecurityUtil() {
    }

    public static String getTargetUrlBasedOnRole(Set<GrantedAuthority> authorities) {
        for (GrantedAuthority grantedAuthority : authorities) {
            log.debug("user role is: {}", grantedAuthority);
            if (RoleConstant.owner().equals(grantedAuthority.getAuthority())) {
                return Routes.OWNER_HOME;
            }
            if (RoleConstant.tutor().equals(grantedAuthority.getAuthority())) {
                return Routes.TUTOR_HOME;
            }
            if (RoleConstant.student().equals(grantedAuthority.getAuthority())) {
                return Routes.STUDENT_HOME;
            }
        }

        return Routes.INDEX;
    }
}