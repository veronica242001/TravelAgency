package com.java.TravelAgency.repository;

import com.java.TravelAgency.entity.Transportation;
import com.java.TravelAgency.utils.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;

import java.text.ParseException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@DataJpaTest
@ActiveProfiles("h2")
@EnableJpaRepositories
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Rollback(false)
@Slf4j
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
public class TransportationRepositoryTest {
    @Autowired
    private TransportationRepository transportationRepository;

    @Test
    @Order(1)
    public void addTransportationTest() throws ParseException {
        Transportation transportation = TransportationsMocks.mockTransportation();
        transportationRepository.save(transportation);
    }

    @Test
    @Order(2)
    public void findAllByTransportationsTest() {
        List<Transportation> transportation = transportationRepository.findAll();
        log.info("transportation list : " + transportation);
        assertFalse(transportation.isEmpty());
        assertEquals(transportation.size(), 1);
    }
}
