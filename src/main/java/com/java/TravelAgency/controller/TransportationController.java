package com.java.TravelAgency.controller;


import com.java.TravelAgency.dto.TransportationDto;
import com.java.TravelAgency.entity.Transportation;
import com.java.TravelAgency.mapper.TransportationMapper;
import com.java.TravelAgency.service.TransportationService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@Slf4j
@RequestMapping("/transportations")
public class TransportationController {

    private final TransportationService transportationService;
    private final TransportationMapper transportationMapper;

  @Autowired
  TransportationController(TransportationService transportationService, TransportationMapper transportationMapper){
        this.transportationService = transportationService;
        this.transportationMapper = transportationMapper;
    }

    @GetMapping()
    public ModelAndView getAllTransportations() {

        log.info("Getting all transportations...");
        ModelAndView modelAndView = new ModelAndView("transportations");
        List<TransportationDto> transportationList = transportationService.getAllTransportations();
        modelAndView.addObject("transportations",transportationList);
        return modelAndView;
    }

    @GetMapping("/{transportationId}")
    public ModelAndView getTransportationById(@PathVariable Long transportationId){
        ModelAndView modelAndView = new ModelAndView("/transportationDetails");
        TransportationDto transportationDto = transportationService.getTransportationById(transportationId);
        modelAndView.addObject("transportationId", transportationId);
        modelAndView.addObject("transportationDto", transportationDto);
        return modelAndView;
    }

    @RequestMapping("/new")
    public String newTransportation(Model model){
        log.info("Adding new transportation...");
        model.addAttribute("transportationDto", new TransportationDto());
        return "transportationForm";
    }

    @PostMapping
    public String saveTransportation(@Valid @ModelAttribute TransportationDto transportationDto, BindingResult bindingResult){
        log.info("Saving new transportation...");
        if(bindingResult.hasErrors()) {
            System.out.println(bindingResult.getAllErrors());
            return "transportationForm";
        }
        Transportation savedTransportation = transportationMapper.mapTotransportation(transportationDto);
        transportationService.addTransportation(savedTransportation);

        return "redirect:/transportations";
    }

    @GetMapping("/updateTransportation/{transportationId}")
    public String editTransportationForm(@PathVariable Long transportationId, Model model) {
        model.addAttribute("transportationDto", transportationService.getTransportationById(transportationId));
        return "editTransportationForm";
    }

    @PostMapping("/updateTransportation/{transportationId}")
    public String editTransportation(@PathVariable Long transportationId,
                             @ModelAttribute("transportationDto") @Valid TransportationDto transportationDto,
                             BindingResult bindingResult){
        log.info("Update the transportation...");
        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult.getAllErrors());
            return "editTransportationForm";
        }
        try{
            transportationService.updateTransportation(transportationId, transportationDto);
        }catch (Exception exception){
            bindingResult.reject("globalError", exception.getMessage());
            return "editTransportationForm";
        }
        return "redirect:/transportations";
    }

    @RequestMapping("/delete/{id}")
    public String deleteTransportation(@PathVariable Long id) {
        log.info("Deleting agency...");
        transportationService.deleteObject(id);
        return "redirect:/transportations";
    }
}
