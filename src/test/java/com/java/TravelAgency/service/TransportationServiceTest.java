package com.java.TravelAgency.service;
import com.java.TravelAgency.constants.Constants;
import com.java.TravelAgency.dto.TransportationDto;
import com.java.TravelAgency.entity.Transportation;
import com.java.TravelAgency.exception.TransportationNotFoundException;
import com.java.TravelAgency.mapper.TransportationMapper;
import com.java.TravelAgency.repository.TransportationRepository;
import com.java.TravelAgency.utils.TransportationsMocks;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TransportationServiceTest {

    @InjectMocks
    TransportationService transportationService;

    @Mock
    TransportationRepository transportationRepository;

    @Mock
    TransportationMapper transportationMapper;

    Transportation transportation;

    TransportationDto transportationDto;

    @Test
    public void testGetAllTransportations() throws ParseException {
        //GIVEN
        transportation = TransportationsMocks.mockTransportation();
        transportationDto = TransportationsMocks.mockTransportationDto();

        List<Transportation> agencies = new ArrayList<>();
        agencies.add(transportation);
        List<TransportationDto> agenciesDto = new ArrayList<>();
        agenciesDto.add(transportationDto);

        //WHEN
        when(transportationRepository.findAll()).thenReturn(agencies);
        when(transportationMapper.mapToTransportationDto(transportation)).thenReturn(transportationDto);

        //THEN
        List<TransportationDto> result = transportationService.getAllTransportations();
        assertEquals(result, agenciesDto);
    }


    @Test
    public void testAddTransportation() throws ParseException {
        //GIVEN
        transportation = TransportationsMocks.mockTransportation();
        transportationDto = TransportationsMocks.mockTransportationDto();

        //WHEN
        when(transportationRepository.save(transportation)).thenReturn(transportation);
        when(transportationMapper.mapToTransportationDto(transportation)).thenReturn(transportationDto);

        TransportationDto result = transportationService.addTransportation(transportation);

        //THEN
        assertTrue(result.getName().equals(transportationDto.getName()));
        assertTrue(result.getType().equals(transportationDto.getType()));
        assertThat(result.getPrice()).isNotNull();
        assertNotNull(result);
    }
    @Test
    public void testGetTransportationById() throws ParseException {
        //GIVEN
        transportation = TransportationsMocks.mockTransportation();
        transportationDto = TransportationsMocks.mockTransportationDto();

        //WHEN
        when(transportationRepository.findById(1L)).thenReturn(Optional.ofNullable(transportation));
        when(transportationMapper.mapToTransportationDto(transportation)).thenReturn(transportationDto);

        //THEN
        TransportationDto result = transportationService.getTransportationById(1L);
        assertEquals(result, transportationDto);
    }
    @Test
    public void testGetTransportationByIdThrowException() {
        //GIVEN
        transportation = null;
        transportationDto = null;

        //WHEN
        when(transportationRepository.findById(1L)).thenReturn(Optional.ofNullable(transportation));
        //THEN
        TransportationNotFoundException TransportationNotFoundException = assertThrows(TransportationNotFoundException.class, () -> transportationService.getTransportationById(1L));
        assertEquals(Constants.TRANSPORTATION_NOT_FOUND, TransportationNotFoundException.getMessage());
    }

    @Test
    public void testDeleteTransportations() throws ParseException {
        //GIVEN
        transportation = TransportationsMocks.mockTransportation();
        transportationDto = TransportationsMocks.mockTransportationDto();

        //WHEN
        when(transportationRepository.findById(transportation.getId())).thenReturn(Optional.ofNullable(transportation));

        //THEN
        Boolean result = transportationService.deleteObject(transportation.getId());
        assertEquals(result, true);
    }

}
