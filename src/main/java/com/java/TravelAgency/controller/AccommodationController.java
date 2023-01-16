package com.java.TravelAgency.controller;


import com.java.TravelAgency.dto.AccommodationDto;
import com.java.TravelAgency.entity.Accommodation;
import com.java.TravelAgency.service.AccommodationService;
import com.java.TravelAgency.constants.Constants;
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
    public ResponseEntity<List<AccommodationDto>> getAllAccommodations() {
        return ResponseEntity.ok(accommodationService.getAll());
    }

    //get accomodation with the longest
    @GetMapping("/longestPeriod")
    public ResponseEntity<List<AccommodationDto>> getAccommodationsWithLongestPeriod() {
        return ResponseEntity.ok(accommodationService.getAccommodationsWithLongestPeriod());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccommodationDto> getAccommodationById(@PathVariable Long id) {
        return ResponseEntity.ok(accommodationService.getAccommodationById(id));
    }

    @PostMapping
    public ResponseEntity<AccommodationDto> addNewAccommodation(@Valid @RequestBody Accommodation accommodation) {
        return ResponseEntity.ok(accommodationService.addAccommodation(accommodation));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAccommodation(@PathVariable Long id) {
        accommodationService.deleteObject(id);
        return ResponseEntity.ok(Constants.OBJECT_DELETED);
    }
}
