package com.java.TravelAgency.service;

import com.java.TravelAgency.constants.Constants;
import com.java.TravelAgency.constants.TestConstants;
import com.java.TravelAgency.dto.CustomerDto;
import com.java.TravelAgency.entity.Customer;
import com.java.TravelAgency.exception.CustomerNotFoundException;
import com.java.TravelAgency.mapper.CustomerMapper;
import com.java.TravelAgency.repository.CustomerRepository;
import com.java.TravelAgency.utils.CustomersMocks;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {

    @InjectMocks
    CustomerService customerService;

    @Mock
    CustomerRepository customerRepository;

    @Mock
    CustomerMapper customerMapper;

    Customer customer;

    CustomerDto customerDto;

    @Test
    public void testGetAllCustomers() throws ParseException {
        //GIVEN
        customer = CustomersMocks.mockCustomer();
        customerDto = CustomersMocks.mockCustomerDto();

        List<Customer> customers = new ArrayList<>();
        customers.add(customer);
        List<CustomerDto> customersDto = new ArrayList<>();
        customersDto.add(customerDto);

        //WHEN
        when(customerRepository.findAll()).thenReturn(customers);
        when(customerMapper.mapToCustomerDto(customer)).thenReturn(customerDto);

        //THEN
        List<CustomerDto> result = customerService.getAllCustomers();
        assertEquals(result, customersDto);
    }


    @Test
    public void testAddCustomer() throws ParseException {
        //GIVEN
        customer = CustomersMocks.mockCustomer();
        customerDto = CustomersMocks.mockCustomerDto();

        //WHEN
        when(customerRepository.save(customer)).thenReturn(customer);
        when(customerMapper.mapToCustomerDto(customer)).thenReturn(customerDto);

        CustomerDto result = customerService.addCustomer(customer);

        //THEN
        assertTrue(result.getFirstName().equals(customerDto.getFirstName()));
        assertTrue(result.getLastName().equals(customerDto.getLastName()));
        assertThat(result.getEmail()).isNotNull();
        assertNotNull(result);
    }
    @Test
    public void testGetCustomersBornAfterYear() throws ParseException {
        //GIVEN
        customer = CustomersMocks.mockCustomer();
        customerDto = CustomersMocks.mockCustomerDto();

        List<Customer> customers = new ArrayList<>();
        customers.add(customer);
        List<CustomerDto> customersDto = new ArrayList<>();
        customersDto.add(customerDto);

        //WHEN
        when(customerRepository.findCustomersBornAfterYear(TestConstants.YEAR)).thenReturn(customers);
        when(customerMapper.mapToCustomerDto(customer)).thenReturn(customerDto);

        //THEN
        List<CustomerDto> result = customerService.getCustomersBornAfterYear(TestConstants.YEAR);
        assertEquals(result, customersDto);
    }
    @Test
    public void testGetCustomersBornAfterYearThrowException() {
        //GIVEN
        customer = null;
        customerDto = null;

        //WHEN
        when(customerRepository.findById(1L)).thenReturn(Optional.ofNullable(customer));
        //THEN
        CustomerNotFoundException CustomerNotFoundException = assertThrows(CustomerNotFoundException.class, () -> customerService.getCustomerById(1L));
        assertEquals(Constants.CUSTOMER_NOT_FOUND, CustomerNotFoundException.getMessage());
    }

    @Test
    public void testDeleteCustomers() throws ParseException {
        //GIVEN
        customer = CustomersMocks.mockCustomer();
        customerDto = CustomersMocks.mockCustomerDto();

        //WHEN
        when(customerRepository.findById(customer.getId())).thenReturn(Optional.ofNullable(customer));

        //THEN
        Boolean result = customerService.deleteCustomer(customer.getId());
        assertEquals(result, true);
    }

}
