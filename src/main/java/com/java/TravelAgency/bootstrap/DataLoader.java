
package com.java.TravelAgency.bootstrap;

import com.java.TravelAgency.entity.security.Authority;
import com.java.TravelAgency.entity.security.User;
import com.java.TravelAgency.repository.security.AuthorityRepository;
import com.java.TravelAgency.repository.security.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
@Profile("mysql")
public class DataLoader implements CommandLineRunner {

    private AuthorityRepository authorityRepository;
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    private void loadUserData() {
        if (userRepository.count() == 0){
            Authority adminRole = authorityRepository.save(Authority.builder().role("ROLE_ADMIN").build());
            Authority guestRole = authorityRepository.save(Authority.builder().role("ROLE_GUEST").build());
            Authority customerRole = authorityRepository.save(Authority.builder().role("ROLE_CUSTOMER").build());

            User admin = User.builder()
                    .username("admin")
                    .password(passwordEncoder.encode("1234"))
                    .authority(adminRole)
                    .build();

            User guest = User.builder()
                    .username("guest")
                    .password(passwordEncoder.encode("1234"))
                    .authority(guestRole)
                    .build();

            User customer = User.builder()
                    .username("vero")
                    .password(passwordEncoder.encode("1234"))
                    .authority(customerRole)
                    .build();


            userRepository.save(admin);
            userRepository.save(guest);
            userRepository.save(customer);
        }
    }


    @Override
    public void run(String... args) throws Exception {
        loadUserData();
    }
}