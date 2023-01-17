package com.java.TravelAgency.controller;

import com.java.TravelAgency.constants.Constants;
import com.java.TravelAgency.dto.CustomerDto;
import com.java.TravelAgency.entity.Customer;
import com.java.TravelAgency.service.CustomerService;
import com.java.TravelAgency.utils.CustomersMocks;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CustomerControllerTest {

    @InjectMocks
    CustomerController customerController;
    @Mock
    CustomerService customerService;
    CustomerDto customerDto;
    Customer customer;


    @Test
    public void getAllCustomersTest() throws ParseException {
        //GIVEN
        customerDto = CustomersMocks.mockCustomerDto();

        List<CustomerDto> customerDtos = new ArrayList<>();
        customerDtos.add(customerDto);

        //WHEN
        when(customerService.getAllCustomers()).thenReturn(customerDtos);

        //THEN
        ResponseEntity<List<CustomerDto>> result = customerController.getAllCustomers();
        assertEquals(result.getBody(), customerDtos);
        assertEquals(result.getStatusCode().value(), 200);
    }




    @Test
    public void addNewCustomerTest() throws ParseException {
        //GIVEN
        customer = CustomersMocks.mockCustomer();

        //WHEN
        when(customerService.addCustomer(customer)).thenReturn(customerDto);

        //THEN
        ResponseEntity<CustomerDto> result = customerController.addNewCustomer(customer);
        assertEquals(result.getBody(), customerDto);
        assertEquals(result.getStatusCode().value(), 200);
    }

    @Test
    public void deleteAdoptedCustomersTest() throws ParseException {
        //GIVEN
        customer = CustomersMocks.mockCustomer();
        //WHEN
        when( customerService.deleteCustomer(customer.getId())).thenReturn(Boolean.valueOf(Constants.OBJECT_DELETED));

        //THEN
        ResponseEntity<String> result = customerController.deleteCustomer(customer.getId());
        assertEquals(result.getBody(), Constants.OBJECT_DELETED);
        assertEquals(result.getStatusCode().value(), 200);
    }



}

