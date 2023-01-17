package com.java.TravelAgency.service;

import com.java.TravelAgency.constants.Constants;
import com.java.TravelAgency.dto.AgentDto;
import com.java.TravelAgency.entity.Agent;
import com.java.TravelAgency.exception.AgentNotFoundException;
import com.java.TravelAgency.mapper.AgentMapper;
import com.java.TravelAgency.repository.AgentRepository;
import com.java.TravelAgency.utils.AgentsMocks;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AgentServiceTest {

    @InjectMocks
    AgentService agentService;

    @Mock
    AgentRepository agentRepository;

    @Mock
    AgentMapper agentMapper;

    Agent agent;

    AgentDto agentDto;

    @Test
    public void testGetAllAgents() {
        //GIVEN
        agent = AgentsMocks.mockAgent();
        agentDto = AgentsMocks.mockAgentDto();

        List<Agent> agents = new ArrayList<>();
        agents.add(agent);
        List<AgentDto> agentsDto = new ArrayList<>();
        agentsDto.add(agentDto);

        //WHEN
        when(agentRepository.findAll()).thenReturn(agents);
        when(agentMapper.mapToAgentDto(agent)).thenReturn(agentDto);

        //THEN
        List<AgentDto> result = agentService.getAllAgents();
        assertEquals(result, agentsDto);
    }


    @Test
    public void testAddAgent() {
        //GIVEN
        agent = AgentsMocks.mockAgent();
        agentDto = AgentsMocks.mockAgentDto();

        //WHEN
        when(agentRepository.save(agent)).thenReturn(agent);
        when(agentMapper.mapToAgentDto(agent)).thenReturn(agentDto);

        AgentDto result = agentService.addAgent(agent);

        //THEN
        assertTrue(result.getFirstName().equals(agentDto.getFirstName()));
        assertTrue(result.getLastName().equals(agentDto.getLastName()));
        assertThat(result.getPhone()).isNotNull();
        assertNotNull(result);
    }
    @Test
    public void testUpdateAgentSalary() {
        //GIVEN
        agent = AgentsMocks.mockAgent();
        agentDto = AgentsMocks.mockAgentDto();
        agentDto.setSalary(agent.getSalary()*(1+0.25));
        //WHEN
        when(agentRepository.findById(1L)).thenReturn(Optional.ofNullable(agent));

        Agent agent2 = AgentsMocks.mockAgent();
        agent2.setSalary(agent.getSalary()*(1+0.25));
        when(agentMapper.mapToAgentDto(agent2)).thenReturn(agentDto);
        when(agentRepository.save(agent)).thenReturn(agent2);

        //THEN
        AgentDto result = agentService.updateSalary(1L, true, 0.25);
        assertThat(result).isNotNull();
        assertTrue(result.getSalary().equals(agent2.getSalary()));
    }
    @Test
    public void testUpdateAgentThrowException() {
        //GIVEN
        agent = null;
        agentDto = null;

        //WHEN
        when(agentRepository.findById(1L)).thenReturn(Optional.ofNullable(agent));
        //THEN
        AgentNotFoundException agentNotFoundException = assertThrows(AgentNotFoundException.class, () -> agentService.updateSalary(1L, true, 0.25));
        assertEquals(Constants.AGENT_NOT_FOUND, agentNotFoundException.getMessage());
    }

    @Test
    public void testDeleteAgent() {
        //GIVEN
        agent = AgentsMocks.mockAgent();
        agentDto = AgentsMocks.mockAgentDto();

        //WHEN
        when(agentRepository.findById(agent.getId())).thenReturn(Optional.ofNullable(agent));

        //THEN
        Boolean result = agentService.deleteAgent(agent.getId());
        assertEquals(result, true);
    }

}
