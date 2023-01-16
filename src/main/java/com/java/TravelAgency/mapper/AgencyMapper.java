package com.java.TravelAgency.mapper;

import com.java.TravelAgency.dto.AgencyDto;
import com.java.TravelAgency.entity.Agency;
import org.springframework.stereotype.Component;

@Component
public class AgencyMapper {

    public Agency mapToAgency(AgencyDto agencyDto) {

        Agency agency = Agency.builder()
                .id(agencyDto.getId())
                .name(agencyDto.getName())
                .address(agencyDto.getAddress())
                .email(agencyDto.getEmail())
                .build();

        return agency;
    }

    public AgencyDto mapToAgencyDto(Agency agency) {

        AgencyDto agencyDto = AgencyDto.builder()
                .id(agency.getId())
                .name(agency.getName())
                .address(agency.getAddress())
                .email(agency.getEmail())
                .build();

        return agencyDto;
    }

}
