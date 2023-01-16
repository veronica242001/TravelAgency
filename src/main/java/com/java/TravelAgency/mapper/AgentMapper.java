package com.java.TravelAgency.mapper;

import com.java.TravelAgency.dto.AgentDto;
import com.java.TravelAgency.entity.Agent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class AgentMapper {
    @Autowired
    AgencyMapper agencyMapper;

    public Agent mapToAgent(AgentDto agentDto) {

        Agent agent = Agent.builder()
                .id(agentDto.getId())
                .firstName(agentDto.getFirstName())
                .lastName(agentDto.getLastName())
                .phone(agentDto.getPhone())
                .salary(agentDto.getSalary())
                ///  .agency(agencyMapper.mapToAgency(agentDto.getAgencyDto()))
                .build();

        return agent;
    }

    public AgentDto mapToAgentDto(Agent agent) {

        AgentDto agentDto = AgentDto.builder()
                .id(agent.getId())
                .firstName(agent.getFirstName())
                .lastName(agent.getLastName())
                .phone(agent.getPhone())
                .salary(agent.getSalary())
                //.agencyDto(agencyMapper.mapToAgencyDto(agent.getAgency()))
                .build();

        return agentDto;
    }
}
