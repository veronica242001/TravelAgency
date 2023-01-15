package com.java.TravelAgency.repository;

import com.java.TravelAgency.entity.Agent;
import com.java.TravelAgency.entity.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OfferRepository extends JpaRepository<Offer, Long> {
}
