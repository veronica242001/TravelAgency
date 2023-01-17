package com.java.TravelAgency.utils;

import com.java.TravelAgency.constants.TestConstants;
import com.java.TravelAgency.dto.TransportationDto;
import com.java.TravelAgency.entity.Transportation;

import java.text.ParseException;
import java.text.SimpleDateFormat;


public class TransportationsMocks {
    public static Transportation mockTransportation() throws ParseException {
        return Transportation.builder()
                .id(TestConstants.ID)
                .name(TestConstants.NAME)
                .type(TestConstants.TYPE)
                .timeStart(new SimpleDateFormat("dd/MM/yyyy").parse("09/09/2022"))
                .timeEnd(new SimpleDateFormat("dd/MM/yyyy").parse("09/10/2022"))
                .addressTo(TestConstants.ADDRESS_TO)
                .addressFrom(TestConstants.ADDRESS_FROM)
                .price(TestConstants.PRICE)
                .build();
    }

    public static TransportationDto mockTransportationDto() throws ParseException {
        return TransportationDto.builder()
                .id(TestConstants.ID)
                .name(TestConstants.NAME)
                .type(TestConstants.TYPE)
                .addressTo(TestConstants.ADDRESS_TO)
                .addressFrom(TestConstants.ADDRESS_FROM)
                .price(TestConstants.PRICE)
                .timeStart(new SimpleDateFormat("dd/MM/yyyy").parse("09/09/2022"))
                .timeEnd(new SimpleDateFormat("dd/MM/yyyy").parse("09/10/2022"))
                .build();
    }
}
