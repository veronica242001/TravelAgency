package com.java.TravelAgency.service;

import com.java.TravelAgency.constants.Constants;
import com.java.TravelAgency.dto.AccommodationDto;
import com.java.TravelAgency.entity.Accommodation;
import com.java.TravelAgency.exception.AccommodationNotFoundException;
import com.java.TravelAgency.mapper.AccommodationMapper;
import com.java.TravelAgency.repository.AccommodationRepository;
import com.java.TravelAgency.utils.AccommodationsMocks;
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
public class AccommodationServiceTest {

    @InjectMocks
    AccommodationService accommodationService;

    @Mock
    AccommodationRepository accommodationRepository;

    @Mock
    AccommodationMapper accommodationMapper;

    Accommodation accommodation;

    AccommodationDto accommodationDto;

    @Test
    public void testGetAllAccommodations() throws ParseException {
        //GIVEN
        accommodation = AccommodationsMocks.mockAccommodation();
        accommodationDto = AccommodationsMocks.mockAccommodationDto();

        List<Accommodation> agencies = new ArrayList<>();
        agencies.add(accommodation);
        List<AccommodationDto> agenciesDto = new ArrayList<>();
        agenciesDto.add(accommodationDto);

        //WHEN
        when(accommodationRepository.findAll()).thenReturn(agencies);
        when(accommodationMapper.mapToaccommodationDto(accommodation)).thenReturn(accommodationDto);

        //THEN
        List<AccommodationDto> result = accommodationService.getAll();
        assertEquals(result, agenciesDto);
    }


    @Test
    public void testAddAccommodation() throws ParseException {
        //GIVEN
        accommodation = AccommodationsMocks.mockAccommodation();
        accommodationDto = AccommodationsMocks.mockAccommodationDto();

        //WHEN
        when(accommodationRepository.save(accommodation)).thenReturn(accommodation);
        when(accommodationMapper.mapToaccommodationDto(accommodation)).thenReturn(accommodationDto);

        AccommodationDto result = accommodationService.addAccommodation(accommodation);

        //THEN
        assertTrue(result.getName().equals(accommodationDto.getName()));
        assertTrue(result.getType().equals(accommodationDto.getType()));
        assertThat(result.getPrice()).isNotNull();
        assertNotNull(result);
    }

    @Test
    public void testGetAccommodationById() throws ParseException {
        //GIVEN
        accommodation = AccommodationsMocks.mockAccommodation();
        accommodationDto = AccommodationsMocks.mockAccommodationDto();

        //WHEN
        when(accommodationRepository.findById(1L)).thenReturn(Optional.ofNullable(accommodation));
        when(accommodationMapper.mapToaccommodationDto(accommodation)).thenReturn(accommodationDto);

        //THEN
        AccommodationDto result = accommodationService.getAccommodationById(1L);
        assertEquals(result, accommodationDto);
    }

    @Test
    public void testGetAccommodationByIdThrowException() {
        //GIVEN
        accommodation = null;
        accommodationDto = null;

        //WHEN
        when(accommodationRepository.findById(1L)).thenReturn(Optional.ofNullable(accommodation));
        //THEN
        AccommodationNotFoundException accommodationNotFoundException = assertThrows(AccommodationNotFoundException.class, () -> accommodationService.getAccommodationById(1L));
        assertEquals(Constants.ACCOMMODATION_NOT_FOUND, accommodationNotFoundException.getMessage());
    }

    @Test
    public void testDeleteAccommodation() throws ParseException {
        //GIVEN
        accommodation = AccommodationsMocks.mockAccommodation();
        accommodationDto = AccommodationsMocks.mockAccommodationDto();

        //WHEN
        when(accommodationRepository.findById(accommodation.getId())).thenReturn(Optional.ofNullable(accommodation));

        //THEN
        Boolean result = accommodationService.deleteObject(accommodation.getId());
        assertEquals(result, true);
    }

}
