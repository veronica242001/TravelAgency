package com.java.TravelAgency.utils;

import com.java.TravelAgency.constants.TestConstants;
import com.java.TravelAgency.dto.AccommodationDto;
import com.java.TravelAgency.entity.Accommodation;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class AccommodationsMocks {
    public static Accommodation mockAccommodation() throws ParseException {
        return Accommodation.builder()
                .id(TestConstants.ID)
                .name(TestConstants.NAME)
                .type(TestConstants.TYPE)
                .address(TestConstants.ADDRESS)
                .price(TestConstants.PRICE)
                .timeStart(new SimpleDateFormat("dd/MM/yyyy").parse("09/09/2022"))
                .timeEnd(new SimpleDateFormat("dd/MM/yyyy").parse("09/10/2022"))
                .build();
    }

    public static AccommodationDto mockAccommodationDto() throws ParseException {
        return AccommodationDto.builder()
                .id(TestConstants.ID)
                .name(TestConstants.NAME)
                .type(TestConstants.TYPE)
                .address(TestConstants.ADDRESS)
                .price(TestConstants.PRICE)
                .timeStart(new SimpleDateFormat("dd/MM/yyyy").parse("09/09/2022"))
                .timeEnd(new SimpleDateFormat("dd/MM/yyyy").parse("09/10/2022"))
                .build();
    }
}
