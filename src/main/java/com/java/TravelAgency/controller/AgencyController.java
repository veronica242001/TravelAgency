package com.java.TravelAgency.controller;


import com.java.TravelAgency.dto.AgencyDto;
import com.java.TravelAgency.entity.Agency;
import com.java.TravelAgency.mapper.AgencyMapper;
import com.java.TravelAgency.service.AgencyService;
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
@RequestMapping("/agencies")
public class AgencyController {
    private final AgencyService agencyService;

    private final AgencyMapper agencyMapper;
    @Autowired
    public AgencyController(AgencyService agencyService, AgencyMapper agencyMapper){
        this.agencyService = agencyService;
        this.agencyMapper = agencyMapper;
    }

    @GetMapping
    public ModelAndView getAllAgencies() {
        log.info("Getting all agencies...");
        ModelAndView modelAndView = new ModelAndView("agencies");
        List<AgencyDto> agencyList = agencyService.getAllAgencies();
        modelAndView.addObject("agencies",agencyList);
        return modelAndView;
    }

    @GetMapping("/{agencyId}")
    public ModelAndView getAgencyById(@PathVariable Long agencyId){
        ModelAndView modelAndView = new ModelAndView("/agencyDetails");
        AgencyDto agencyDto = agencyService.getAgencyById(agencyId);
        modelAndView.addObject("agencyId", agencyId);
        modelAndView.addObject("agencyDto", agencyDto);
        return modelAndView;
    }

    @RequestMapping("/new")
    public String newAgency(Model model){
        log.info("Adding new agency...");
        model.addAttribute("agencyDto", new AgencyDto());
        return "agencyForm";
    }
    @PostMapping
    public String saveAgency(@Valid @ModelAttribute AgencyDto agencyDto, BindingResult bindingResult){
        log.info("Saving new agency...");
        if(bindingResult.hasErrors()) {
            System.out.println(bindingResult.getAllErrors());
            return "agencyForm";
        }
        Agency savedAgency = agencyMapper.mapToAgency(agencyDto);
        agencyService.addAgency(savedAgency);

        return "redirect:/agencies";
    }

    @GetMapping("/updateAgency/{agencyId}")
    public String editAgencyForm(@PathVariable Long agencyId, Model model) {
        model.addAttribute("agencyDto", agencyService.getAgencyById(agencyId));
        return "editAgencyForm";
    }
    @PostMapping("/updateAgency/{agencyId}")
    public String editAgency(@PathVariable Long agencyId,
                           @ModelAttribute("agencyDto") @Valid AgencyDto agencyDto,
                           BindingResult bindingResult, Model model){
        log.info("Update the  agency...");
        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult.getAllErrors());
            return "editAgencyForm";
        }
        try{
            agencyService.updateAgency(agencyId, agencyDto);
        }catch (Exception exception){
            bindingResult.reject("globalError", exception.getMessage());
            return "editAgencyForm";
        }
        return "redirect:/agencies";
    }
    @RequestMapping("/delete/{id}")
    public String deleteAgency(@PathVariable Long id) {
        log.info("Deleting agency...");
        agencyService.deleteAgency(id);
        return "redirect:/agencies";
    }

}
