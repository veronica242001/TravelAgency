package com.java.TravelAgency.controller;

import com.java.TravelAgency.constants.Constants;
import com.java.TravelAgency.dto.AgentDto;
import com.java.TravelAgency.entity.Agent;
import com.java.TravelAgency.service.AgentService;
import com.java.TravelAgency.utils.AgentsMocks;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AgentControllerTest {

    @InjectMocks
    AgentController agentController;
    @Mock
    AgentService agentService;
    AgentDto agentDto;
    Agent agent;


    @Test
    public void getAllAgentsTest() {
        //GIVEN
        agentDto = AgentsMocks.mockAgentDto();

        List<AgentDto> agentDtos = new ArrayList<>();
        agentDtos.add(agentDto);

        //WHEN
        when(agentService.getAllAgents()).thenReturn(agentDtos);

        //THEN
        ResponseEntity<List<AgentDto>> result = agentController.getAllAgents();
        assertEquals(result.getBody(), agentDtos);
        assertEquals(result.getStatusCode().value(), 200);
    }
    

  

    @Test
    public void addNewAgentTest() {
        //GIVEN
        agent = AgentsMocks.mockAgent();

        //WHEN
        when(agentService.addAgent(agent)).thenReturn(agentDto);

        //THEN
        ResponseEntity<AgentDto> result = agentController.addNewAgent(agent);
        assertEquals(result.getBody(), agentDto);
        assertEquals(result.getStatusCode().value(), 200);
    }

    @Test
    public void deleteAdoptedAgentsTest() {
        //GIVEN
        agent = AgentsMocks.mockAgent();
        //WHEN
        when( agentService.deleteAgent(agent.getId())).thenReturn(Boolean.valueOf(Constants.OBJECT_DELETED));

        //THEN
        ResponseEntity<String> result = agentController.deleteAgent(agent.getId());
        assertEquals(result.getBody(), Constants.OBJECT_DELETED);
        assertEquals(result.getStatusCode().value(), 200);
    }



}

