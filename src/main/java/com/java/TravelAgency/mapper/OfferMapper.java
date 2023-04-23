package com.java.TravelAgency.mapper;

import com.java.TravelAgency.dto.OfferDto;
import com.java.TravelAgency.entity.Offer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class OfferMapper {
    @Autowired
    AgentMapper agentMapper;
    @Autowired
    AccommodationMapper accommodationMapper;
    @Autowired
    TransportationMapper transportationMapper;

    @Autowired
    CustomerMapper customerMapper;

    public Offer mapToOffer(OfferDto offerDto) {

        Offer offer = Offer.builder()
                .id(offerDto.getId())
                .bookDate(offerDto.getBookDate())
                .accommodations(offerDto.getAccommodationsDto().stream().map(o -> accommodationMapper.mapToAccommodation(o)).collect(Collectors.toList()))
                .transportations(offerDto.getTransportationsDto().stream().map(o -> transportationMapper.mapTotransportation(o)).collect(Collectors.toList()))
                .agent(agentMapper.mapToAgent(offerDto.getAgentDto()))
                .customer(customerMapper.mapToCustomer(offerDto.getCustomerDto()))
                .build();

        return offer;
    }

    public OfferDto mapToOfferDto(Offer offer) {

        OfferDto offerDto = OfferDto.builder()
                .id(offer.getId())
                .bookDate(offer.getBookDate())
                .accommodationsDto(offer.getAccommodations().stream().map(o -> accommodationMapper.mapToAccommodationDto(o)).collect(Collectors.toList()))
                .transportationsDto(offer.getTransportations().stream().map(o -> transportationMapper.mapToTransportationDto(o)).collect(Collectors.toList()))
                .agentDto(agentMapper.mapToAgentDto(offer.getAgent()))
                .customerDto(customerMapper.mapToCustomerDto(offer.getCustomer()))
                .build();

        return offerDto;
    }
}
