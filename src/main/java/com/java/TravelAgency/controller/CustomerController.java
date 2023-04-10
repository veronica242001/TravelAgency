package com.java.TravelAgency.controller;

import com.java.TravelAgency.dto.CustomerDto;
import com.java.TravelAgency.entity.Customer;
import com.java.TravelAgency.entity.security.Authority;
import com.java.TravelAgency.entity.security.User;
import com.java.TravelAgency.mapper.CustomerMapper;
import com.java.TravelAgency.repository.security.AuthorityRepository;
import com.java.TravelAgency.service.CustomerService;
import com.java.TravelAgency.constants.Constants;
import com.java.TravelAgency.service.security.UserService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@Slf4j
//@RequestMapping("/customers")
public class CustomerController {

     @Autowired
    private CustomerMapper customerMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserService userService;
    @Autowired
    private AuthorityRepository authorityRepository;
    @Autowired
    private CustomerService customerService;

    @GetMapping("/access_denied")
    public String accessDenied() {
        return "accessDenied";
    }

    @GetMapping("/login-error")
    public String loginError(Model model) {
        return "loginError";
    }

    @GetMapping(path = "/login")
    public String loginForm() {
        return "login";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String registerForm(Model model){
        log.info("Register customer...");
        model.addAttribute("customerDto", new CustomerDto());

        return "registerForm";
    }

    @PostMapping("/register")
    public ModelAndView register(@Valid @RequestBody @ModelAttribute CustomerDto customerDto, BindingResult bindingResult){
        ModelAndView viewRegister = new ModelAndView("registerForm");
        if(bindingResult.hasErrors()){
            System.out.println(bindingResult.getAllErrors());
            return viewRegister;
        }

        Customer customer = customerMapper.mapToCustomer(customerDto);
        customer.setPassword(passwordEncoder.encode(customer.getPassword()));
        customer.setEnabled(1);
        customerService.addCustomer(customer);

        Set<Authority> authorities = new HashSet<>();
        authorities.add(authorityRepository.findAuthorityByRole("ROLE_CUSTOMER"));
        User user = new User(customer.getId(), customer.getUsername(), customer.getPassword(), authorities, true, true, true, true);
        userService.save(user);

        ModelAndView model= new ModelAndView("login");

        return model;
    }

    @GetMapping("/customers")
    public ModelAndView getCustomers() {
        log.info("Getting customers...");
        ModelAndView modelAndView = new ModelAndView("customers");
        List<CustomerDto> customers = customerService.getAllCustomers();
        modelAndView.addObject("customers", customers);

        return modelAndView;
    }

    @RequestMapping("/customers/delete/{id}")
    @Transactional
    public String deleteByCardNumber(@PathVariable Long id){
        log.info("Deleting customer...");
        customerService.deleteCustomer(id);

        return "redirect:/customers";
    }

    @RequestMapping("/customers/update/{id}")
    public String update(@PathVariable Long id, Model model){
        log.info("Updating customer info...");
        CustomerDto customerDto = customerService.getCustomerById(id);
        model.addAttribute("customer", customerDto);

        return "updateCustomerForm";
    }

    @PostMapping("/customers/update")
    @Transactional
    public String updateCustomer(@Valid @ModelAttribute Customer customer,
                                 BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            System.out.println(bindingResult.getAllErrors());
            return "updateCustomerForm";
        }

        customer.setPassword(passwordEncoder.encode(customer.getPassword()));
        customerService.addCustomer(customer);

        User user = userService.findByUsername(customer.getUsername());
        user.setPassword(customer.getPassword());
        userService.save(user);

        return "redirect:/customers";
    }


//    @GetMapping()
//    public ResponseEntity<List<CustomerDto>> getAllCustomers() {
//        return ResponseEntity.ok(customerService.getAllCustomers());
//    }
//
//    @GetMapping("year/{year}")
//    public ResponseEntity<List<CustomerDto>> getCustomersBornAfterYear(@PathVariable Long year) {
//        return ResponseEntity.ok(customerService.getCustomersBornAfterYear(year));
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<CustomerDto> getCustomerById(@PathVariable Long id) {
//        return ResponseEntity.ok(customerService.getCustomerById(id));
//    }
//
//    @PostMapping()
//    public ResponseEntity<CustomerDto> addNewCustomer(@Valid @RequestBody Customer customer) {
//        return ResponseEntity.ok(customerService.addCustomer(customer));
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<String> deleteCustomer(@PathVariable Long id) {
//        customerService.deleteCustomer(id);
//        return ResponseEntity.ok(Constants.OBJECT_DELETED);
//    }


}
