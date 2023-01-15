package com.java.TravelAgency.service;


import com.java.TravelAgency.dto.AgentDto;
import com.java.TravelAgency.entity.Agent;
import com.java.TravelAgency.exception.AgentAlreadyExistsException;
import com.java.TravelAgency.exception.AgentNotFoundException;
import com.java.TravelAgency.mapper.AgentMapper;
import com.java.TravelAgency.repository.AgentRepository;
import constants.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AgentService {

    @Autowired
    private AgentRepository agentRepository;

    @Autowired
    private AgentMapper agentMapper;


    public List<AgentDto> getAllAgents(){
        return agentRepository.findAll()
                .stream().map(a -> agentMapper.mapToAgentDto(a))
                .collect(Collectors.toList());
    }
    public AgentDto getAgentById(Long id) {
        Optional<Agent> agent = agentRepository.findById(id);
        if (agent.isEmpty()) {
            throw new AgentNotFoundException(String.format(Constants.AGENT_NOT_FOUND, id));
        }
        return agentMapper.mapToAgentDto(agent.get());
    }

    public AgentDto addAgent(Agent agent) {
        if(agentRepository.findAgentByName(agent.getFirstName(), agent.getLastName()).isPresent()){ // if the name already exists, throw exception
            throw new AgentAlreadyExistsException(String.format(Constants.AGENT_EXISTS,agent.getFirstName()+" "+agent.getLastName()));
        }
        return agentMapper.mapToAgentDto(agentRepository.save(agent));
    }
    public boolean deleteAgent(Long id) {
        Optional<Agent> agent = agentRepository.findById(id);
        if (agent.isEmpty()) {
            throw new AgentNotFoundException(String.format(Constants.AGENT_NOT_FOUND, id));
        }
        agentRepository.delete(agent.get());
        return true;
    }

}
