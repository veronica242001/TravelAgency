package com.java.TravelAgency.controller;

import com.java.TravelAgency.constants.Constants;
import com.java.TravelAgency.dto.AccommodationDto;
import com.java.TravelAgency.entity.Accommodation;
import com.java.TravelAgency.mapper.AccommodationMapper;
import com.java.TravelAgency.service.AccommodationService;
import com.java.TravelAgency.utils.AccommodationsMocks;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import java.text.ParseException;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AccommodationControllerTest {

    @InjectMocks
    AccommodationController accommodationController;
    @Mock
    AccommodationService accommodationService;
    @Mock
    AccommodationMapper accommodationMapper;
    @Mock
    Model model;
    @Mock
    BindingResult bindingResult;
    AccommodationDto accommodationDto;
    Accommodation accommodation;


    @Test
    public void getAccommodationByIdTest() throws ParseException {
        //GIVEN
        accommodationDto = AccommodationsMocks.mockAccommodationDto();

        //WHEN
        when(accommodationService.getAccommodationById(1L)).thenReturn(accommodationDto);

        //THEN
        ModelAndView modelAndViewAccommodation = accommodationController.getAccommodationById(1L);
        assertEquals("/accommodationDetails", modelAndViewAccommodation.getViewName());
        verify(accommodationService, times(1)).getAccommodationById(1L);
    }



    @Test
    public void saveAccommodationTest() throws ParseException {
        //GIVEN
        accommodationDto = AccommodationsMocks.mockAccommodationDto();

        //WHEN
        when(accommodationService.addAccommodation(accommodationMapper.mapToAccommodation(accommodationDto))).thenReturn(accommodationDto);

        //THEN
        String viewName = accommodationController.saveAccommodation(accommodationDto, bindingResult);
        assertEquals("redirect:/accommodations", viewName);
        verify(accommodationService, times(1)).addAccommodation(accommodationMapper.mapToAccommodation(accommodationDto));
    }

    @Test
    public void deleteaccommodationTest() throws ParseException {
        //GIVEN
        accommodationDto = AccommodationsMocks.mockAccommodationDto();

        //WHEN
        when(accommodationService.deleteObject(1L)).thenReturn(true);

        //THEN
        String viewName = accommodationController.deleteAccommodation(1L);
        assertEquals("redirect:/accommodations", viewName);
        verify(accommodationService, times(1)).deleteObject(1L);
    }



}

