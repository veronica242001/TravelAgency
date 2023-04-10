package com.java.TravelAgency.repository.security;


import com.java.TravelAgency.entity.security.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepository extends JpaRepository<Authority, Integer> {
    Authority findAuthorityByRole(String role);
}
