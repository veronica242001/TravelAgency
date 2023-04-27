package com.java.TravelAgency.configuration;

import com.java.TravelAgency.service.security.JpaUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@Profile("mysql")
public class SecurityJpaConfig {

    private final JpaUserDetailsService userDetailsService;

    public SecurityJpaConfig(JpaUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeRequests(auth -> auth
                        .requestMatchers("/register", "/login", "/loginError", "/","/home","",
                        "/agents/register", "/agents", "/customers/register", "/customers","/accommodations",
                         "/transportations","/offers/all","/agencies").permitAll()
                        .requestMatchers( "/agencies/updateAgency/{agencyId}", "/offers","/offers/{id}",
                         "/accommodations/new", "/accommodations/{accommodationId}",
                        "/accommodation/updateAccommodation/{accommodationId}","/accommodations/delete/{id}",
                        "/transportations/new", "/transportations/{transportationId}",
                         "/transportations/updateTransportation/{transportationsId}","/transportations/delete/{id}").hasAnyRole("ADMIN", "ROLE_AGENT","ROLE_CUSTOMER")
                        .requestMatchers(  "/agencies/delete/{agencyId}", "agencies/new",
                        "/agents/delete/{id}","/agents/updateAgent/{agentId}",
                        "/customers/delete/{id}","/customers/update/{id}" ).hasRole("ADMIN")
//                        .anyRequest().authenticated()
                )
                .userDetailsService(userDetailsService)
                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/authUser")
                .failureUrl("/loginError")
                .successForwardUrl("/home")
                .permitAll()
                .and()
                .exceptionHandling()
                .accessDeniedPage("/accessDenied")
                .and()
                .httpBasic(withDefaults())
                .build();
    }
    public AuthenticationSuccessHandler loginSuccessHandler() {
        return (request, response, authentication) -> response.sendRedirect("/");
    }

    public AuthenticationFailureHandler loginFailureHandler() {
        return (request, response, exception) -> {
            response.sendRedirect("/loginError");
        };
    }

}

