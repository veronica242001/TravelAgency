package com.java.TravelAgency.utils;

import com.java.TravelAgency.constants.TestConstants;
import com.java.TravelAgency.dto.*;
import com.java.TravelAgency.entity.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class OffersMocks {
    public static Offer mockOffer() throws ParseException {
        return Offer.builder()
                .id(TestConstants.ID)
                .agent(Agent.builder().id(TestConstants.ID).build())
                .bookDate(new SimpleDateFormat("dd/MM/yyyy").parse("09/09/2022"))
                .customer(Customer.builder().id(TestConstants.ID).build())
                .accommodations(Stream.of(Accommodation.builder().id(TestConstants.ID).build()).collect(Collectors.toList()))
                .transportations(Stream.of(Transportation.builder().build()).collect(Collectors.toList()))
                .build();
    }

    public static OfferDto mockOfferDto() throws ParseException {
        return OfferDto.builder()
                .id(TestConstants.ID)
                .agentDto(AgentDto.builder().id(TestConstants.ID).build())
                .bookDate(new SimpleDateFormat("dd/MM/yyyy").parse("09/09/2022"))
                .customerDto(CustomerDto.builder().id(TestConstants.ID).build())
                .accommodationsDto(Stream.of(AccommodationDto.builder().id(TestConstants.ID).build()).collect(Collectors.toList()))
                .transportationsDto(Stream.of(TransportationDto.builder().build()).collect(Collectors.toList()))
                .build();
    }
}
