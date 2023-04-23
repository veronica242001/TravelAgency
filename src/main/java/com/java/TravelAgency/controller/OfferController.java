package com.java.TravelAgency.controller;


import com.java.TravelAgency.dto.OfferDto;
import com.java.TravelAgency.entity.Offer;
import com.java.TravelAgency.mapper.OfferMapper;
import com.java.TravelAgency.service.*;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@Slf4j
@RequestMapping("/offers")
public class OfferController {

    private final OfferService offerService;
    private final OfferMapper offerMapper;
    private final AgentService agentService;
    private final CustomerService customerService;
    private final TransportationService transportationService;
    private final AccommodationService accommodationService;
    @Autowired
    public OfferController(OfferService offerService, OfferMapper offerMapper, AgentService agentService, CustomerService customerService, TransportationService transportationService, AccommodationService accommodationService) {
        this.offerService = offerService;
        this.offerMapper = offerMapper;
        this.agentService = agentService;
        this.customerService = customerService;
        this.transportationService = transportationService;
        this.accommodationService = accommodationService;
    }

//    @GetMapping()
    public ModelAndView getOffers() {
        log.info("Getting offers...");
        ModelAndView modelAndView = new ModelAndView("offers");
        List<OfferDto> offers = offerService.getAllOffers();
        modelAndView.addObject("offers", offers);

        return modelAndView;
    }

    @RequestMapping("")
    public String getOffersPage(Model model,
                               @RequestParam("page") Optional<Integer> page,
                               @RequestParam("size") Optional<Integer> size) {
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(3);
        Page<OfferDto> offerPage = offerService.findPaginatedOffers(PageRequest.of(currentPage - 1, pageSize));
        model.addAttribute("offerPage",offerPage);
        return "offersPaginated";
    }

    @GetMapping("/{id}")
    public ResponseEntity<OfferDto> getOfferById(@PathVariable Long id) {
        return ResponseEntity.ok(offerService.getOfferById(id));
    }
    @RequestMapping("/new")
    public String newOffer(Model model){
        log.info("Adding new offer...");
        model.addAttribute("agents", agentService.getAllAgents());
        model.addAttribute("customers", customerService.getAllCustomers());
        model.addAttribute("transportations", transportationService.getAllTransportations());
        model.addAttribute("accommodations",accommodationService.getAllAccommodations());
        model.addAttribute("offerDto", new OfferDto());
        return "offerForm";
    }
    @PostMapping
    public String saveOffer( @RequestParam("transportationSelectedValues") @NotNull List<Long> transportationSelectedValues,
                            @RequestParam("accommodationSelectedValues") @NotNull List<Long> accommodationSelectedValues,
                            @Valid @RequestBody @ModelAttribute OfferDto offerDto,
                            BindingResult bindingResult, Model model){
        log.info("Saving new offer...");

        System.out.println(transportationSelectedValues.get(0));
        offerDto.setAccommodationsDto(accommodationSelectedValues.stream()
                .map(id ->accommodationService.getAccommodationById(id)).collect(Collectors.toList()));
        offerDto.setTransportationsDto(transportationSelectedValues.stream()
                .map(id ->transportationService.getTransportationById(id)).collect(Collectors.toList()));
        offerDto.setAgentDto(agentService.getAgentById(offerDto.getAgentDto().getId()));
        offerDto.setCustomerDto(customerService.getCustomerById(offerDto.getAgentDto().getId()));
        model.addAttribute("agents", agentService.getAllAgents());
        model.addAttribute("customers", customerService.getAllCustomers());
        model.addAttribute("transportations", transportationService.getAllTransportations());
        model.addAttribute("accommodations",accommodationService.getAllAccommodations());

        if(bindingResult.hasErrors()) {
            System.out.println(bindingResult.getAllErrors());
            return "offerForm";
        }
        Offer savedOffer = offerMapper.mapToOffer(offerDto);
        offerService.addOffer(savedOffer);

        return "redirect:/offers";
    }

    @RequestMapping("/delete/{offerId}")
    public String deleteOffer(@PathVariable Long offerId){
        offerService.deleteOffer(offerId);
        return "redirect:/offers";
    }
}
