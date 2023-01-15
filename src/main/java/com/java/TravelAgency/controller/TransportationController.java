package com.java.TravelAgency.controller;


import com.java.TravelAgency.dto.TransportationDto;
import com.java.TravelAgency.entity.Transportation;
import com.java.TravelAgency.service.TransportationService;
import constants.Constants;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transportations")
public class TransportationController {

    @Autowired
    TransportationService transportationService;

    @GetMapping()
    public ResponseEntity<List<TransportationDto>> getAllTransportations(){
        return ResponseEntity.ok(transportationService.getAllTransportations());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransportationDto> getTransportationById(@PathVariable Long id){
        return ResponseEntity.ok(transportationService.getTransportationById(id));
    }

    @PostMapping
    public ResponseEntity<TransportationDto> addNewTransportation(@Valid @RequestBody Transportation transportation){
        return ResponseEntity.ok(transportationService.addTransportation(transportation));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTransportation(@PathVariable Long id){
        transportationService.deleteObject(id);
        return ResponseEntity.ok(Constants.OBJECT_DELETED);
    }
}
