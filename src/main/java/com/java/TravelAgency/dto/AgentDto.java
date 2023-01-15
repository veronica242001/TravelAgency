package com.java.TravelAgency.dto;


import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AgentDto {

    private Long id;

    private String firstName;

    private String lastName;

    private String phone;

    private Double salary;

    private AgencyDto agencyDto;

}
