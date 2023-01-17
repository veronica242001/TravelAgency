package com.java.TravelAgency.controller;

import com.java.TravelAgency.constants.Constants;
import com.java.TravelAgency.dto.AgencyDto;
import com.java.TravelAgency.entity.Agency;
import com.java.TravelAgency.service.AgencyService;
import com.java.TravelAgency.utils.AgenciesMocks;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AgencyControllerTest {

    @InjectMocks
    AgencyController agencyController;
    @Mock
    AgencyService agencyService;
    AgencyDto agencyDto;
    Agency agency;


    @Test
    public void getAllAgencysTest() {
        //GIVEN
        agencyDto = AgenciesMocks.mockAgencyDto();

        List<AgencyDto> agenciesDto= new ArrayList<>();
        agenciesDto.add(agencyDto);

        //WHEN
        when(agencyService.getAllAgencies()).thenReturn(agenciesDto);

        //THEN
        ResponseEntity<List<AgencyDto>> result = agencyController.getAllAgencies();
        assertEquals(result.getBody(), agenciesDto);
        assertEquals(result.getStatusCode().value(), 200);
    }




    @Test
    public void addNewAgencyTest() {
        //GIVEN
        agency = AgenciesMocks.mockAgency();

        //WHEN
        when(agencyService.addAgency(agency)).thenReturn(agencyDto);

        //THEN
        ResponseEntity<AgencyDto> result = agencyController.addNewAgency(agency);
        assertEquals(result.getBody(), agencyDto);
        assertEquals(result.getStatusCode().value(), 200);
    }

    @Test
    public void deleteAdoptedAgencysTest() {
        //GIVEN
        agency = AgenciesMocks.mockAgency();
        //WHEN
        when( agencyService.deleteAgency(agency.getId())).thenReturn(Boolean.valueOf(Constants.OBJECT_DELETED));

        //THEN
        ResponseEntity<String> result = agencyController.deleteAgency(agency.getId());
        assertEquals(result.getBody(), Constants.OBJECT_DELETED);
        assertEquals(result.getStatusCode().value(), 200);
    }



}

