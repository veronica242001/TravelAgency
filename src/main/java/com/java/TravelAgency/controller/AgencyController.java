package com.java.TravelAgency.controller;


import com.java.TravelAgency.dto.AgencyDto;
import com.java.TravelAgency.entity.Agency;
import com.java.TravelAgency.mapper.AgencyMapper;
import com.java.TravelAgency.service.AgencyService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
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

//    @GetMapping("/{id}")
//    public ResponseEntity<AgencyDto> getAgencyById(@PathVariable Long id) {
//        return ResponseEntity.ok(agencyService.getAgencyById(id));
//    }

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

//    //update the name for an agency
//    @PatchMapping("/{id}/{name}")
//    public ResponseEntity<AgencyDto> updateUsername(@PathVariable Long id, @PathVariable String name) {
//        return ResponseEntity.ok(agencyService.updateName(id, name));
//    }
//
    @RequestMapping("/delete/{id}")
    public String deleteAgency(@PathVariable Long id) {
        log.info("Deleting agency...");
        agencyService.deleteAgency(id);
        return "redirect:/agencies";
    }

}
