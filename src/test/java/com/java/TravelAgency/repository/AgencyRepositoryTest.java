package com.java.TravelAgency.repository;

import com.java.TravelAgency.entity.Agency;
import com.java.TravelAgency.utils.AgenciesMocks;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@DataJpaTest
@ActiveProfiles("h2")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Rollback(false)
@Slf4j
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
public class AgencyRepositoryTest {

    @Autowired
    private AgencyRepository agencyRepository;


    @Test
    @Order(1)
    public void addAgencyTest() {
        Agency agency = AgenciesMocks.mockAgency();
        agencyRepository.save(agency);
    }

    @Test
    @Order(2)
    public void findAllByAgencyIdTest() {
        List<Agency> agencies = agencyRepository.findAll();
        log.info("Agency list : " + agencies);
        assertFalse(agencies.isEmpty());
        assertEquals(agencies.size(), 1);
    }




}