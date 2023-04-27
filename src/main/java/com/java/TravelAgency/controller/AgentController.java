package com.java.TravelAgency.controller;


import com.java.TravelAgency.dto.AgentDto;
import com.java.TravelAgency.entity.Agent;
import com.java.TravelAgency.entity.security.Authority;
import com.java.TravelAgency.entity.security.User;
import com.java.TravelAgency.mapper.AgentMapper;
import com.java.TravelAgency.mapper.CustomerMapper;
import com.java.TravelAgency.repository.security.AuthorityRepository;
import com.java.TravelAgency.service.AgencyService;
import com.java.TravelAgency.service.AgentService;
import com.java.TravelAgency.service.security.UserService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@Slf4j
@RequestMapping("/agents")
public class AgentController {

    private final AgentService agentService;
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;
    private final AuthorityRepository authorityRepository;
    private final AgentMapper agentMapper;
    private final AgencyService agencyService;

    @Autowired
    public AgentController(AgentService agentService, PasswordEncoder passwordEncoder, UserService userService, AuthorityRepository authorityRepository, AgentMapper agentMapper, AgencyService agencyService) {
        this.agentService = agentService;
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
        this.authorityRepository = authorityRepository;
        this.agentMapper = agentMapper;
        this.agencyService= agencyService;
    }

    @GetMapping(value = "/register")
    public String registerForm(Model model){
        log.info("Register Agent...");
        model.addAttribute("allAgencies", agencyService.getAllAgencies());
        model.addAttribute("agentDto", new AgentDto());

        return "agentForm";
    }

    @PostMapping("/register")
    public String register(@Valid @RequestBody @ModelAttribute AgentDto agentDto, BindingResult bindingResult, Model model){

        model.addAttribute("allAgencies", agencyService.getAllAgencies());
        if(bindingResult.hasErrors()){
            System.out.println(bindingResult.getAllErrors());
            return "agentForm";
        }

        Agent agent = agentMapper.mapToAgent(agentDto);
        agent.setPassword(passwordEncoder.encode(agent.getPassword()));
        agent.setEnabled(1);
        agentService.addAgent(agent);

        Set<Authority> authorities = new HashSet<>();
        authorities.add(authorityRepository.findAuthorityByRole("ROLE_AGENT"));
        User user = User.builder()
                .username(agent.getUsername())
                .password(agent.getPassword())
                .authorities(authorities)
                .enabled(true)
                .accountNotExpired(true)
                .accountNotLocked(true)
                .credentialsNotExpired(true)
                .build();
        userService.save(user);

        return "redirect:/login";
    }

    @GetMapping()
    public ModelAndView getAgents() {
        log.info("Getting Agents...");
        ModelAndView modelAndView = new ModelAndView("agents");
        List<AgentDto> agents = agentService.getAllAgents();
        modelAndView.addObject("agents", agents);

        return modelAndView;
    }

    @RequestMapping("/delete/{id}")
    @Transactional
    public String deleteAgent(@PathVariable Long id){
        log.info("Deleting Agent...");
        agentService.deleteAgent(id);

        return "redirect:/agents";
    }

    @GetMapping("/updateAgent/{agentId}")
    public String update(@PathVariable Long agentId, Model model){
        log.info("Updating Agent info...");
        model.addAttribute("agentDto", agentService.getAgentById(agentId));
        model.addAttribute("allAgencies", agencyService.getAllAgencies());
        return "editAgentForm";
    }

    @PostMapping("/updateAgent/{agentId}")
     public String updateAgent(@PathVariable Long agentId,
                                @Valid @ModelAttribute AgentDto agentDto,
                                 BindingResult bindingResult, Model model){
        model.addAttribute("allAgencies", agencyService.getAllAgencies());
        if (bindingResult.hasErrors()){
            System.out.println(bindingResult.getAllErrors());
            return "editAgentForm";
        }
        try{
        agentDto.setPassword(passwordEncoder.encode(agentDto.getPassword()));
        agentDto.setUsername(agentService.getAgentById(agentId).getUsername());
        agentService.addAgent(agentMapper.mapToAgent(agentDto));

        User user = userService.findByUsername(agentDto.getUsername());
        user.setPassword(agentDto.getPassword());
        userService.save(user);
        }catch (Exception exception){
            bindingResult.reject("globalError", exception.getMessage());
            return "editAgentForm";
        }
        return "redirect:/agents";
    }


}
