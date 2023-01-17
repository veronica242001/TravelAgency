package com.java.TravelAgency.utils;

import com.java.TravelAgency.constants.TestConstants;
import com.java.TravelAgency.dto.AgencyDto;
import com.java.TravelAgency.entity.Agency;

public class AgenciesMocks {
    public static Agency mockAgency(){
        return Agency.builder()
                .id(TestConstants.ID)
                .name(TestConstants.NAME)
                .email(TestConstants.EMAIL)
                .address(TestConstants.ADDRESS)
                .build();
    }

    public static AgencyDto mockAgencyDto(){
        return AgencyDto.builder()
                .id(TestConstants.ID)
                .name(TestConstants.NAME)
                .email(TestConstants.EMAIL)
                .address(TestConstants.ADDRESS)
                .build();
    }
}
