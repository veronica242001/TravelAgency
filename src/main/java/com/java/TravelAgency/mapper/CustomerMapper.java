package com.java.TravelAgency.mapper;

import com.java.TravelAgency.dto.CustomerDto;
import com.java.TravelAgency.entity.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {


    public Customer mapToCustomer( CustomerDto customerDto){

        Customer customer = Customer.builder()
                            .id(customerDto.getId())
                            .firstName(customerDto.getFirstName())
                            .lastName(customerDto.getLastName())
                            .email(customerDto.getEmail())
                            .address(customerDto.getAddress())
                            .gender(customerDto.getGender())
                            .birthDate(customerDto.getBirthDate())
                            .build();

        return customer;
    }

    public CustomerDto mapToCustomerDto( Customer customer){

        CustomerDto customerDto = CustomerDto.builder()
                .id(customer.getId())
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .email(customer.getEmail())
                .address(customer.getAddress())
                .gender(customer.getGender())
                .birthDate(customer.getBirthDate())
                .build();

        return customerDto;
    }

}
