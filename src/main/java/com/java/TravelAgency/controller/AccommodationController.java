package com.java.TravelAgency.controller;



import com.java.TravelAgency.dto.AccommodationDto;
import com.java.TravelAgency.entity.Accommodation;
import com.java.TravelAgency.mapper.AccommodationMapper;
import com.java.TravelAgency.service.AccommodationService;
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
@RequestMapping("/accommodations")
public class AccommodationController {

    private final AccommodationService accommodationService;
    private final AccommodationMapper accommodationMapper;
    @Autowired
    public AccommodationController(AccommodationService AccommodationService, AccommodationMapper AccommodationMapper){
        this.accommodationService = AccommodationService;
        this.accommodationMapper = AccommodationMapper;
    }

    @GetMapping
    public ModelAndView getAllAccommodations() {
        log.info("Getting all accommodations...");
        ModelAndView modelAndView = new ModelAndView("accommodations");
        List<AccommodationDto> accommodationList = accommodationService.getAllAccommodations();
        modelAndView.addObject("accommodations",accommodationList);
        return modelAndView;
    }


    //get accomodation with the longest
//    @GetMapping("/longestPeriod")
//    public ResponseEntity<List<AccommodationDto>> getAccommodationsWithLongestPeriod() {
//        return ResponseEntity.ok(accommodationService.getAccommodationsWithLongestPeriod());
//    }

    @GetMapping("/{accommodationId}")
    public ModelAndView getAccommodationById(@PathVariable Long accommodationId){
        ModelAndView modelAndView = new ModelAndView("/accommodationDetails");
        AccommodationDto accommodationDto = accommodationService.getAccommodationById(accommodationId);
        modelAndView.addObject("accommodationId", accommodationId);
        modelAndView.addObject("accommodationDto", accommodationDto);
        return modelAndView;
    }
    @RequestMapping("/new")
    public String accommodation(Model model){
        log.info("Adding new accommodation...");
        model.addAttribute("accommodationDto", new AccommodationDto());
        return "accommodationForm";
    }
    @PostMapping
    public String saveAccommodation(@Valid @ModelAttribute AccommodationDto accommodationDto, BindingResult bindingResult){
        log.info("Saving new accommodation...");
        if(bindingResult.hasErrors()) {
            System.out.println(bindingResult.getAllErrors());
            return "accommodationForm";
        }
        Accommodation savedAccommodation = accommodationMapper. mapToAccommodation(accommodationDto);
        accommodationService.addAccommodation(savedAccommodation);

        return "redirect:/accommodations";
    }
    @GetMapping("/updateAccommodation/{accommodationId}")
    public String editAccommodationForm(@PathVariable Long accommodationId, Model model) {
        model.addAttribute("accommodationDto", accommodationService.getAccommodationById(accommodationId));
        return "editAccommodationForm";
    }
    @PostMapping("/updateAccommodation/{accommodationId}")
    public String editAccommodation(@PathVariable Long accommodationId,
                                     @ModelAttribute("accommodationDto") @Valid AccommodationDto accommodationDto,
                                     BindingResult bindingResult, Model model){
        log.info("Update the accommodation...");
        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult.getAllErrors());
            return "editAccommodationForm";
        }
        try{
            accommodationService.updateAccommodation(accommodationId, accommodationDto);
        }catch (Exception exception){
            bindingResult.reject("globalError", exception.getMessage());
            return "editAccommodationForm";
        }
        return "redirect:/accommodations";
    }

    @RequestMapping("/delete/{id}")
    public String deleteAccommodation(@PathVariable Long id) {
        log.info("Deleting accommodation...");
        accommodationService.deleteObject(id);
        return "redirect:/accommodations";
    }

}
