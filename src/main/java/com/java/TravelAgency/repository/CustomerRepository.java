package com.java.TravelAgency.repository;

import com.java.TravelAgency.entity.Agent;
import com.java.TravelAgency.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {

    @Query("SELECT ag FROM Customer ag WHERE ag.firstName = ?1 and ag.lastName = ?2")
    Optional<Agent> findCustomerByName(String firstName, String lastName);
}
