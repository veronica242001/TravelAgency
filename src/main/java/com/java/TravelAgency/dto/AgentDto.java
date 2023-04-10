package com.java.TravelAgency.dto;


import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AgentDto {

    private Long id;

    private String firstName;

    private String lastName;

    private String phone;

    private Double salary;

    private AgencyDto agencyDto;

}
