package com.java.TravelAgency.dto;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AgencyDto {

    private Long id;

    private String name;

    private String email;

    private String address;

}
