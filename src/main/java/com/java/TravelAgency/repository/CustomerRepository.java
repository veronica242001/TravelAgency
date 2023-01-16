package com.java.TravelAgency.repository;

import com.java.TravelAgency.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Query("SELECT ag FROM Customer ag WHERE ag.firstName = ?1 and ag.lastName = ?2")
    Optional<Customer> findCustomerByName(String firstName, String lastName);

    @Query("SELECT c FROM Customer c WHERE EXTRACT(YEAR FROM c.birthDate) >= ?1 ")
    List<Customer> findCustomersBornAfterYear(Long year);
}
