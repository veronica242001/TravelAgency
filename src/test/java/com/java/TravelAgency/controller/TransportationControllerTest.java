package com.java.TravelAgency.controller;

import com.java.TravelAgency.constants.Constants;
import com.java.TravelAgency.dto.TransportationDto;
import com.java.TravelAgency.entity.Transportation;
import com.java.TravelAgency.service.TransportationService;
import com.java.TravelAgency.utils.TransportationsMocks;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TransportationControllerTest {

    @InjectMocks
    TransportationController transportationController;
    @Mock
    TransportationService transportationService;
    TransportationDto transportationDto;
    Transportation transportation;


    @Test
    public void getAllTransportationsTest() throws ParseException {
        //GIVEN
        transportationDto = TransportationsMocks.mockTransportationDto();

        List<TransportationDto> transportationDtos = new ArrayList<>();
        transportationDtos.add(transportationDto);

        //WHEN
        when(transportationService.getAllTransportations()).thenReturn(transportationDtos);

        //THEN
        ResponseEntity<List<TransportationDto>> result = transportationController.getAllTransportations();
        assertEquals(result.getBody(), transportationDtos);
        assertEquals(result.getStatusCode().value(), 200);
    }



    @Test
    public void addNewTransportationTest() throws ParseException {
        //GIVEN
        transportation = TransportationsMocks.mockTransportation();

        //WHEN
        when(transportationService.addTransportation(transportation)).thenReturn(transportationDto);

        //THEN
        ResponseEntity<TransportationDto> result = transportationController.addNewTransportation(transportation);
        assertEquals(result.getBody(), transportationDto);
        assertEquals(result.getStatusCode().value(), 200);
    }

    @Test
    public void deleteAdoptedTransportationsTest() throws ParseException {
        //GIVEN
        transportation = TransportationsMocks.mockTransportation();
        //WHEN
        when( transportationService.deleteObject(transportation.getId())).thenReturn(Boolean.valueOf(Constants.OBJECT_DELETED));

        //THEN
        ResponseEntity<String> result = transportationController.deleteTransportation(transportation.getId());
        assertEquals(result.getBody(), Constants.OBJECT_DELETED);
        assertEquals(result.getStatusCode().value(), 200);
    }



}

