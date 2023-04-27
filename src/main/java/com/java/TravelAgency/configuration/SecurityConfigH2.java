package com.java.TravelAgency.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@Profile("h2")
public class SecurityConfigH2{


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(User.withUsername("vero")
                .password(passwordEncoder.encode("1234"))
                .roles("ROLE_CUSTOMER")
                .build());
        manager.createUser(User.withUsername("admin")
                .password(passwordEncoder.encode("1234"))
                .roles("ROLE_AGENT", "ADMIN")
                .build());
        return manager;
    }



    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.ignoringRequestMatchers("/h2-console/**"))
                .authorizeRequests(auth -> auth
                        .requestMatchers("/h2-console/**").permitAll()
                        .requestMatchers("/register", "/login", "/loginError", "/","/home","",
                                "/agents/register", "/agents", "/customers/register", "/customers","/accommodations",
                                "/transportations","/offers","/agencies").permitAll()
                        .requestMatchers( "/agencies/updateAgency/{agencyId}", "/offers","/offers/{id}",
                                "/accommodations/new", "/accommodations/{accommodationId}",
                                "/accommodation/updateAccommodation/{accommodationId}","/accommodations/delete/{id}",
                                "/transportations/new", "/transportations/{transportationId}",
                                "/transportations/updateTransportation/{transportationsId}","/transportations/delete/{id}").permitAll()
                        .requestMatchers(  "/agencies/delete/{agencyId}", "agencies/new",
                                "/agents/delete/{id}","/agents/updateAgent/{agentId}",
                                "/customers/delete/{id}","/customers/update/{id}" ).permitAll()
                      //  .anyRequest().authenticated()
                )
                .headers(headers -> headers.frameOptions().sameOrigin())
                .httpBasic(withDefaults())
                .build();
    }

}

