package com.java.TravelAgency.mapper;

import com.java.TravelAgency.dto.TransportationDto;
import com.java.TravelAgency.entity.Transportation;
import org.springframework.stereotype.Component;

@Component
public class TransportationMapper {
    public Transportation mapTotransportation(TransportationDto transportationDto){

        Transportation transportation = Transportation.builder()
                .id(transportationDto.getId())
                .name(transportationDto.getName())
                .type(transportationDto.getType())
                .addressFrom(transportationDto.getAddressFrom())
                .addressTo(transportationDto.getAddressTo())
                .price(transportationDto.getPrice())
                .timeEnd(transportationDto.getTimeEnd())
                .timeStart(transportationDto.getTimeStart())
                .price(transportationDto.getPrice())
                .build();

        return transportation;
    }

    public TransportationDto mapToTransportationDto( Transportation transportation){

        TransportationDto transportationDto = TransportationDto.builder()
                .id(transportation.getId())
                .name(transportation.getName())
                .type(transportation.getType())
                .addressFrom(transportation.getAddressFrom())
                .addressTo(transportation.getAddressTo())
                .price(transportation.getPrice())
                .timeEnd(transportation.getTimeEnd())
                .timeStart(transportation.getTimeStart())
                .price(transportation.getPrice())
                .build();

        return transportationDto;
    }
}
