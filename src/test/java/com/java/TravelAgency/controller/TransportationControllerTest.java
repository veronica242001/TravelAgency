package com.java.TravelAgency.controller;

import com.java.TravelAgency.dto.TransportationDto;
import com.java.TravelAgency.entity.Transportation;
import com.java.TravelAgency.mapper.TransportationMapper;
import com.java.TravelAgency.service.TransportationService;
import com.java.TravelAgency.utils.TransportationsMocks;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TransportationControllerTest {

    @InjectMocks
    TransportationController transportationController;
    @Mock
    TransportationService transportationService;
    @Mock
    TransportationMapper transportationMapper;
    @Mock
    Model model;
    @Mock
    BindingResult bindingResult;
    TransportationDto transportationDto;
    Transportation transportation;


    @Test
    public void getTransportationByIdTest() throws ParseException {
        //GIVEN
        transportationDto = TransportationsMocks.mockTransportationDto();

        //WHEN
        when(transportationService.getTransportationById(1L)).thenReturn(transportationDto);

        //THEN
        ModelAndView modelAndViewTransportation = transportationController.getTransportationById(1L);
        assertEquals("/transportationDetails", modelAndViewTransportation.getViewName());
        verify(transportationService, times(1)).getTransportationById(1L);
    }



    @Test
    public void saveTransportationTest() throws ParseException {
        //GIVEN
        transportationDto = TransportationsMocks.mockTransportationDto();

        //WHEN
        when(transportationService.addTransportation(transportationMapper.mapTotransportation(transportationDto))).thenReturn(transportationDto);

        //THEN
        String viewName = transportationController.saveTransportation(transportationDto, bindingResult);
        assertEquals("redirect:/transportations", viewName);
        verify(transportationService, times(1)).addTransportation(transportationMapper.mapTotransportation(transportationDto));
    }

    @Test
    public void deleteTransportationTest() throws ParseException {
        //GIVEN
        transportationDto = TransportationsMocks.mockTransportationDto();

        //WHEN
        when(transportationService.deleteObject(1L)).thenReturn(true);

        //THEN
        String viewName = transportationController.deleteTransportation(1L);
        assertEquals("redirect:/transportations", viewName);
        verify(transportationService, times(1)).deleteObject(1L);
    }

}



