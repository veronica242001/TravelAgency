package com.java.TravelAgency.controller;

import com.java.TravelAgency.constants.Constants;
import com.java.TravelAgency.dto.AgencyDto;
import com.java.TravelAgency.dto.AgentDto;
import com.java.TravelAgency.dto.AgentDto;
import com.java.TravelAgency.entity.Agent;
import com.java.TravelAgency.entity.Agent;
import com.java.TravelAgency.mapper.AgentMapper;
import com.java.TravelAgency.service.AgencyService;
import com.java.TravelAgency.service.AgentService;
import com.java.TravelAgency.service.AgentService;
import com.java.TravelAgency.utils.AgenciesMocks;
import com.java.TravelAgency.utils.AgentsMocks;
import com.java.TravelAgency.utils.AgentsMocks;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AgentControllerTest {
    @InjectMocks
    AgentController agentController;
    @Mock
    AgentService agentService;
    @Mock
    AgencyService agencyService;
    @Mock
    AgentMapper agentMapper;
    @Mock
    Model model;
    @Mock
    BindingResult bindingResult;
    AgentDto agentDto;
    Agent agent;


    @Test
    public void deleteAgentTest() throws ParseException {
        //GIVEN
        agentDto = AgentsMocks.mockAgentDto();

        //WHEN
        when(agentService.deleteAgent(1L)).thenReturn(true);

        //THEN
        String viewName = agentController.deleteAgent(1L);
        assertEquals("redirect:/agents", viewName);
        verify(agentService, times(1)).deleteAgent(1L);
    }

}

