package com.java.TravelAgency.repository;

import com.java.TravelAgency.entity.Transportation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface TransportationRepository extends JpaRepository<Transportation, Long> {

    Optional<Transportation> findByName(String name);

    @Query("SELECT t FROM Transportation t WHERE t.name = ?1 and t.addressFrom = ?2")
    Optional<Transportation> findByNameAndAddressfrom(String name, String addressFrom);
}
