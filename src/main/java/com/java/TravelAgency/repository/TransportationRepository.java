package com.java.TravelAgency.repository;


import com.java.TravelAgency.entity.Customer;
import com.java.TravelAgency.entity.Transportation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TransportationRepository extends JpaRepository<Transportation,Long> {

    Optional<Transportation> findByName(String name);
}
