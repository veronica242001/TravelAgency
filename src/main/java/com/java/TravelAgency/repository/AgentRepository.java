package com.java.TravelAgency.repository;


import com.java.TravelAgency.entity.Agency;
import com.java.TravelAgency.entity.Agent;
import jdk.jfr.Registered;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

@Registered
public interface AgentRepository extends JpaRepository<Agent, Long> {

    @Query("SELECT ag FROM Agent ag WHERE ag.firstName = ?1 and ag.lastName = ?2")
    Optional<Agent> findAgentByName(String firstName, String lastName);
}
