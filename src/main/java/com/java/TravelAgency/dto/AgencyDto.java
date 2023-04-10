package com.java.TravelAgency.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AgencyDto {

    private Long id;

    private String name;

    private String email;

    private String address;

}
