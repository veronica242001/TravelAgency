package com.java.TravelAgency.repository;

import com.java.TravelAgency.entity.Accommodation;
import com.java.TravelAgency.entity.Agency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccommodationRepository extends JpaRepository<Accommodation, Long>{


    Optional<Accommodation> findByName(String name);
}
