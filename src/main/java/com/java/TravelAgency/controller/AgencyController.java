package com.java.TravelAgency.controller;


import com.java.TravelAgency.dto.AgencyDto;
import com.java.TravelAgency.entity.Agency;
import com.java.TravelAgency.service.AgencyService;
import com.java.TravelAgency.constants.Constants;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/agencies")
public class AgencyController {

    @Autowired
    AgencyService agencyService;

    @GetMapping()
    public ResponseEntity<List<AgencyDto>> getAllAgencies() {
        return ResponseEntity.ok(agencyService.getAllAgencies());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AgencyDto> getAgencyById(@PathVariable Long id) {
        return ResponseEntity.ok(agencyService.getAgencyById(id));
    }

    @PostMapping
    public ResponseEntity<AgencyDto> addNewAgency(@Valid @RequestBody Agency agency) {
        return ResponseEntity.ok(agencyService.addAgency(agency));
    }

    //update the name for an agency
    @PatchMapping("/{id}/{name}")
    public ResponseEntity<AgencyDto> updateUsername(@PathVariable Long id, @PathVariable String name) {
        return ResponseEntity.ok(agencyService.updateName(id, name));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAgency(@PathVariable Long id) {
        agencyService.deleteAgency(id);
        return ResponseEntity.ok(Constants.OBJECT_DELETED);
    }

}
