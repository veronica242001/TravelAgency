package com.java.TravelAgency.dto;


import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class CustomerDto {

    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private String gender;

    private String address;

    private Date birthDate;
}
