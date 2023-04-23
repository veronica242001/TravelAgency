package com.java.TravelAgency.service;

import com.java.TravelAgency.constants.Constants;
import com.java.TravelAgency.constants.TestConstants;
import com.java.TravelAgency.dto.AgencyDto;
import com.java.TravelAgency.entity.Agency;
import com.java.TravelAgency.exception.AgencyNotFoundException;
import com.java.TravelAgency.mapper.AgencyMapper;
import com.java.TravelAgency.repository.AgencyRepository;
import com.java.TravelAgency.utils.AgenciesMocks;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AgencyServiceTest {

    @InjectMocks
    AgencyService agencyService;

    @Mock
    AgencyRepository agencyRepository;

    @Mock
    AgencyMapper agencyMapper;

    Agency agency;

    AgencyDto agencyDto;

    @Test
    public void testGetAllAgencies() {
        //GIVEN
        agency = AgenciesMocks.mockAgency();
        agencyDto = AgenciesMocks.mockAgencyDto();

        List<Agency> agencies = new ArrayList<>();
        agencies.add(agency);
        List<AgencyDto> agenciesDto = new ArrayList<>();
        agenciesDto.add(agencyDto);

        //WHEN
        when(agencyRepository.findAll()).thenReturn(agencies);
        when(agencyMapper.mapToAgencyDto(agency)).thenReturn(agencyDto);

        //THEN
        List<AgencyDto> result = agencyService.getAllAgencies();
        assertEquals(result, agenciesDto);
    }


    @Test
    public void testAddAgency() {
        //GIVEN
        agency = AgenciesMocks.mockAgency();
        agencyDto = AgenciesMocks.mockAgencyDto();

        //WHEN
        when(agencyRepository.save(agency)).thenReturn(agency);
        when(agencyMapper.mapToAgencyDto(agency)).thenReturn(agencyDto);

        AgencyDto result = agencyService.addAgency(agency);

        //THEN
        assertTrue(result.getName().equals(agencyDto.getName()));
        assertThat(result.getEmail()).isNotNull();
        assertNotNull(result);
    }

    @Test
    public void testDeleteAgencies() {
        //GIVEN
        agency = AgenciesMocks.mockAgency();
        agencyDto = AgenciesMocks.mockAgencyDto();

        //WHEN
        when(agencyRepository.findById(agency.getId())).thenReturn(Optional.ofNullable(agency));

        //THEN
        Boolean result = agencyService.deleteAgency(agency.getId());
        assertEquals(result, true);
    }

}
