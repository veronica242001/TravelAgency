package com.java.TravelAgency.controller;

import com.java.TravelAgency.dto.CustomerDto;
import com.java.TravelAgency.entity.Customer;
import com.java.TravelAgency.service.CustomerService;
import constants.Constants;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping()
    public ResponseEntity<List<CustomerDto>> getAllCustomers() {
        return ResponseEntity.ok(customerService.getAllCustomers());
    }

    @GetMapping("year/{year}")
    public ResponseEntity<List<CustomerDto>> getCustomersBornAfterYear(@PathVariable Long year) {
        return ResponseEntity.ok(customerService.getCustomersBornAfterYear(year));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDto> getCustomerById(@PathVariable Long id) {
        return ResponseEntity.ok(customerService.getCustomerById(id));
    }

    @PostMapping()
    public ResponseEntity<CustomerDto> addNewCustomer(@Valid @RequestBody Customer customer) {
        return ResponseEntity.ok(customerService.addCustomer(customer));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
        return ResponseEntity.ok(Constants.OBJECT_DELETED);
    }


}
