package com.java.TravelAgency.utils;

import com.java.TravelAgency.constants.TestConstants;
import com.java.TravelAgency.dto.AgencyDto;
import com.java.TravelAgency.dto.AgentDto;
import com.java.TravelAgency.entity.Agency;
import com.java.TravelAgency.entity.Agent;

public class AgentsMocks {
    public static Agent mockAgent(){
        return Agent.builder()
                .id(TestConstants.ID)
                .firstName(TestConstants.FIRST_NAME)
                .lastName(TestConstants.LAST_NAME)
                .phone(TestConstants.PHONE_NUMBER)
                .salary(TestConstants.SALARY)
                .password("1234")
                .agency(Agency.builder()
                        .name(TestConstants.NAME)
                        .email(TestConstants.EMAIL)
                        .build())
                .build();
    }

    public static AgentDto mockAgentDto(){
        return AgentDto.builder()
                .id(TestConstants.ID)
                .firstName(TestConstants.FIRST_NAME)
                .lastName(TestConstants.LAST_NAME)
                .phone(TestConstants.PHONE_NUMBER)
                .salary(TestConstants.SALARY)
                .password("1234")
                .agencyDto(AgencyDto.builder()
                        .name(TestConstants.NAME)
                        .email(TestConstants.EMAIL)
                        .build())
                .build();
    }
}
