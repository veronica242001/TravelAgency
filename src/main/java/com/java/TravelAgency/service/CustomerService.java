package com.java.TravelAgency.service;

import com.java.TravelAgency.dto.CustomerDto;
import com.java.TravelAgency.exception.CustomerAlreadyExistsException;
import com.java.TravelAgency.exception.CustomerNotFoundException;
import com.java.TravelAgency.mapper.CustomerMapper;

import com.java.TravelAgency.entity.Customer;

import com.java.TravelAgency.repository.CustomerRepository;
import constants.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerMapper customerMapper;


    public List<CustomerDto> getAllCustomers() {
        return customerRepository.findAll()
                .stream().map(a -> customerMapper.mapToCustomerDto(a))
                .collect(Collectors.toList());
    }

    public List<CustomerDto> getCustomersBornAfterYear(Long year) {
        return customerRepository.findCustomersBornAfterYear(year)
                .stream().map(a -> customerMapper.mapToCustomerDto(a))
                .collect(Collectors.toList());
    }

    public CustomerDto getCustomerById(Long id) {
        Optional<Customer> customer = customerRepository.findById(id);
        if (customer.isEmpty()) {
            throw new CustomerNotFoundException(String.format(Constants.CUSTOMER_NOT_FOUND, id));
        }
        return customerMapper.mapToCustomerDto(customer.get());
    }

    public CustomerDto addCustomer(Customer customer) {
        if (customerRepository.findCustomerByName(customer.getFirstName(), customer.getLastName()).isPresent()) { // if the name already exists, throw exception
            throw new CustomerAlreadyExistsException(String.format(Constants.CUSTOMER_EXISTS, customer.getFirstName() + " " + customer.getLastName()));
        }
        return customerMapper.mapToCustomerDto(customerRepository.save(customer));
    }

    public boolean deleteCustomer(Long id) {
        Optional<Customer> customer = customerRepository.findById(id);
        if (customer.isEmpty()) {
            throw new CustomerNotFoundException(String.format(Constants.CUSTOMER_NOT_FOUND, id));
        }
        customerRepository.delete(customer.get());
        return true;
    }


}
