package com.java.TravelAgency.repository;

import com.java.TravelAgency.entity.Accommodation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccommodationRepository extends JpaRepository<Accommodation, Long> {


    Optional<Accommodation> findByName(String name);

    @Query("SELECT a1 FROM Accommodation a1  WHERE a1.timeEnd - a1.timeStart= ( SELECT max(a2.timeEnd - a2.timeStart) FROM Accommodation a2)")
    List<Accommodation> getAccommodationsWithLongestPeriod();
}
