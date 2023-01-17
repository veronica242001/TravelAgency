package com.java.TravelAgency.controller;

import com.java.TravelAgency.constants.Constants;
import com.java.TravelAgency.dto.AccommodationDto;
import com.java.TravelAgency.entity.Accommodation;
import com.java.TravelAgency.service.AccommodationService;
import com.java.TravelAgency.utils.AccommodationsMocks;
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
public class AccommodationControllerTest {

    @InjectMocks
    AccommodationController accommodationController;
    @Mock
    AccommodationService accommodationService;
    AccommodationDto accommodationDto;
    Accommodation accommodation;


    @Test
    public void getAllAccommodationsTest() throws ParseException {
        //GIVEN
        accommodationDto = AccommodationsMocks.mockAccommodationDto();

        List<AccommodationDto> accommodationDtos = new ArrayList<>();
        accommodationDtos.add(accommodationDto);

        //WHEN
        when(accommodationService.getAll()).thenReturn(accommodationDtos);

        //THEN
        ResponseEntity<List<AccommodationDto>> result = accommodationController.getAllAccommodations();
        assertEquals(result.getBody(), accommodationDtos);
        assertEquals(result.getStatusCode().value(), 200);
    }



    @Test
    public void addNewAccommodationTest() throws ParseException {
        //GIVEN
        accommodation = AccommodationsMocks.mockAccommodation();

        //WHEN
        when(accommodationService.addAccommodation(accommodation)).thenReturn(accommodationDto);

        //THEN
        ResponseEntity<AccommodationDto> result = accommodationController.addNewAccommodation(accommodation);
        assertEquals(result.getBody(), accommodationDto);
        assertEquals(result.getStatusCode().value(), 200);
    }

    @Test
    public void deleteAdoptedAccommodationsTest() throws ParseException {
        //GIVEN
        accommodation = AccommodationsMocks.mockAccommodation();
        //WHEN
        when( accommodationService.deleteObject(accommodation.getId())).thenReturn(Boolean.valueOf(Constants.OBJECT_DELETED));

        //THEN
        ResponseEntity<String> result = accommodationController.deleteAccommodation(accommodation.getId());
        assertEquals(result.getBody(), Constants.OBJECT_DELETED);
        assertEquals(result.getStatusCode().value(), 200);
    }



}

