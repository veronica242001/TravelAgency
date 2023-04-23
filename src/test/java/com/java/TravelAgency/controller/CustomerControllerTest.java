package com.java.TravelAgency.controller;

import com.java.TravelAgency.constants.Constants;
import com.java.TravelAgency.dto.CustomerDto;
import com.java.TravelAgency.entity.Customer;
import com.java.TravelAgency.service.CustomerService;
import com.java.TravelAgency.utils.CustomersMocks;
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
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CustomerControllerTest {

    @InjectMocks
    CustomerController customerController;
    @Mock
    CustomerService customerService;
    CustomerDto customerDto;
    Customer customer;


    @Test
    public void deleteCustomerTest() throws ParseException {
        //GIVEN
        customerDto = CustomersMocks.mockCustomerDto();

        //WHEN
        when(customerService.deleteCustomer(1L)).thenReturn(true);

        //THEN
        String viewName = customerController.deleteByCardNumber (1L);
        assertEquals("redirect:/customers", viewName);
        verify(customerService, times(1)).deleteCustomer(1L);
    }
}

