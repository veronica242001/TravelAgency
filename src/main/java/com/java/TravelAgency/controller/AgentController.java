package com.java.TravelAgency.controller;


import com.java.TravelAgency.dto.AgentDto;
import com.java.TravelAgency.entity.Agent;
import com.java.TravelAgency.service.AgentService;
import constants.Constants;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/agents")
public class AgentController {
    @Autowired
    AgentService agentService;

    @GetMapping()
    public ResponseEntity<List<AgentDto>> getAllAgents(){
        return ResponseEntity.ok(agentService.getAllAgents());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AgentDto> getAgentById(@PathVariable Long id){
        return ResponseEntity.ok(agentService.getAgentById(id));
    }

    @PostMapping
    public ResponseEntity<AgentDto> addNewAgent(@Valid @RequestBody Agent agent){
        return ResponseEntity.ok(agentService.addAgent(agent));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAgent(@PathVariable Long id){
        agentService.deleteAgent(id);
        return ResponseEntity.ok(Constants.OBJECT_DELETED);
    }
}
