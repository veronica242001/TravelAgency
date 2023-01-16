package com.java.TravelAgency.controller;


import com.java.TravelAgency.dto.OfferDto;
import com.java.TravelAgency.entity.Offer;
import com.java.TravelAgency.service.OfferService;
import com.java.TravelAgency.constants.Constants;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/offers")
public class OfferController {

    @Autowired
    OfferService offerService;

    @GetMapping()
    public ResponseEntity<List<OfferDto>> getAllOffers() {
        return ResponseEntity.ok(offerService.getAllOffers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OfferDto> getOfferById(@PathVariable Long id) {
        return ResponseEntity.ok(offerService.getOfferById(id));
    }

    @PostMapping
    public ResponseEntity<OfferDto> addNewOffer(@Valid @RequestBody Offer offer) {
        return ResponseEntity.ok(offerService.addOffer(offer));
    }

    @PutMapping()
    public ResponseEntity<OfferDto> updateOffer(@Valid @RequestBody Offer offer) {
        return ResponseEntity.ok(offerService.updateOffer(offer));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOffer(@PathVariable Long id) {
        offerService.deleteOffer(id);
        return ResponseEntity.ok(Constants.OBJECT_DELETED);
    }
}
