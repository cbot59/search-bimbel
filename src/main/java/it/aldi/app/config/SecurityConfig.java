package it.aldi.app.config;

import it.aldi.app.controller.Routes;
import it.aldi.app.security.AuthSuccessHandler;
import it.aldi.app.security.CustomAccessDeniedHandler;
import it.aldi.app.security.service.BimbelUserDetailsService;
import it.aldi.app.util.RoleConstant;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.session.HttpSessionEventPublisher;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private @NonNull BimbelUserDetailsService bimbelUserDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
            .antMatchers("/resources/static/**", "/webjars/**").permitAll()
            .antMatchers("/register/**", "/signin/**").anonymous()
            .antMatchers("/", "/logout/**").authenticated()
            .antMatchers(HttpMethod.PATCH, Routes.API + Routes.ORGANIZATIONS + "/**").hasAuthority(RoleConstant.OWNER)
            .antMatchers(Routes.TUTOR_HOME + "/**").hasAuthority(RoleConstant.TUTOR)
            .antMatchers(Routes.OWNER_HOME + "/**").hasAuthority(RoleConstant.OWNER)
            .antMatchers(Routes.STUDENT_HOME + "/**").hasAuthority(RoleConstant.STUDENT)
            .and()
            .formLogin()
            .loginPage(Routes.SIGNIN)
            .permitAll(false)
            .successHandler(authenticationSuccessHandler())
            .and()
            .logout()
            .logoutUrl(Routes.LOGOUT)
            .logoutSuccessUrl(Routes.SIGNIN + "?logout")
            .deleteCookies("JSESSIONID")
            .permitAll(false)
            .and()
            .exceptionHandling()
            .accessDeniedHandler(accessDeniedHandler())
            .and()
            .sessionManagement()
            .maximumSessions(1)
            .maxSessionsPreventsLogin(true);
    }

    @EnableGlobalMethodSecurity(prePostEnabled = true, jsr250Enabled = true)
    private static class GlobalSecurityConfiguration extends GlobalMethodSecurityConfiguration {

        public GlobalSecurityConfiguration() {
        }

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider =
            new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        daoAuthenticationProvider.setUserDetailsService(bimbelUserDetailsService);
        return daoAuthenticationProvider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(bimbelUserDetailsService)
            .passwordEncoder(passwordEncoder())
            .and()
            .authenticationProvider(authenticationProvider());
    }

    @Bean
    public HttpSessionEventPublisher httpSessionEventPublisher() {
        return new HttpSessionEventPublisher();
    }

    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return new CustomAccessDeniedHandler();
    }

    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler() {
        return new AuthSuccessHandler();
    }
}
