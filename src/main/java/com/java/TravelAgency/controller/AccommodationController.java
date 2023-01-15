package com.java.TravelAgency.controller;


import com.java.TravelAgency.dto.AccommodationDto;
import com.java.TravelAgency.entity.Accommodation;
import com.java.TravelAgency.service.AccommodationService;
import constants.Constants;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accommodations")
public class AccommodationController {
    @Autowired
    AccommodationService accommodationService;

    @GetMapping()
    public ResponseEntity<List<AccommodationDto>> getAllaccommodations(){
        return ResponseEntity.ok(accommodationService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccommodationDto> getaccommodationById(@PathVariable Long id){
        return ResponseEntity.ok(accommodationService.getAccommodationById(id));
    }

    @PostMapping
    public ResponseEntity<AccommodationDto> addNewaccommodation(@Valid @RequestBody Accommodation accommodation){
        return ResponseEntity.ok(accommodationService.addAccommodation(accommodation));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteaccommodation(@PathVariable Long id){
        accommodationService.deleteObject(id);
        return ResponseEntity.ok(Constants.OBJECT_DELETED);
    }
}
