package com.java.TravelAgency.mapper;

import com.java.TravelAgency.dto.AccommodationDto;
import com.java.TravelAgency.entity.Accommodation;
import org.springframework.stereotype.Component;

@Component
public class AccommodationMapper {

    public Accommodation mapToaccommodation(AccommodationDto accommodationDto) {

        Accommodation accommodation = Accommodation.builder()
                .id(accommodationDto.getId())
                .name(accommodationDto.getName())
                .type(accommodationDto.getType())
                .address(accommodationDto.getAddress())
                .price(accommodationDto.getPrice())
                .timeEnd(accommodationDto.getTimeEnd())
                .timeStart(accommodationDto.getTimeStart())
                .price(accommodationDto.getPrice())
                //.offers(accommodationDto.getOffers())
                .build();

        return accommodation;
    }

    public AccommodationDto mapToaccommodationDto(Accommodation accommodation) {

        AccommodationDto accommodationDto = AccommodationDto.builder()
                .id(accommodation.getId())
                .name(accommodation.getName())
                .type(accommodation.getType())
                .address(accommodation.getAddress())
                .price(accommodation.getPrice())
                .timeEnd(accommodation.getTimeEnd())
                .timeStart(accommodation.getTimeStart())
                .price(accommodation.getPrice())
                //.offers(accommodation.getOffers())
                .build();

        return accommodationDto;
    }
}
