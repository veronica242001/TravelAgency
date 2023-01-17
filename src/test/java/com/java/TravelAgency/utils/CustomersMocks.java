package com.java.TravelAgency.utils;

import com.java.TravelAgency.constants.TestConstants;
import com.java.TravelAgency.dto.AgencyDto;
import com.java.TravelAgency.dto.CustomerDto;
import com.java.TravelAgency.entity.Agency;
import com.java.TravelAgency.entity.Customer;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class CustomersMocks {
    public static Customer mockCustomer() throws ParseException {
        return Customer.builder()
                .id(TestConstants.ID)
                .firstName(TestConstants.FIRST_NAME)
                .lastName(TestConstants.LAST_NAME)
                .email(TestConstants.EMAIL)
                .birthDate(new SimpleDateFormat("dd/MM/yyyy").parse("09/10/2000") )
                .build();
    }

    public static CustomerDto mockCustomerDto() throws ParseException {
        return CustomerDto.builder()
                .id(TestConstants.ID)
                .firstName(TestConstants.FIRST_NAME)
                .lastName(TestConstants.LAST_NAME)
                .email(TestConstants.EMAIL)
                .birthDate(new SimpleDateFormat("dd/MM/yyyy").parse("09/10/2000") )
                .build();
    }
}
