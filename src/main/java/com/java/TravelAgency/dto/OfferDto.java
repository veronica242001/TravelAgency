package com.java.TravelAgency.dto;

import com.java.TravelAgency.entity.Accommodation;
import com.java.TravelAgency.entity.Agent;
import com.java.TravelAgency.entity.Customer;
import com.java.TravelAgency.entity.Transportation;
import lombok.Builder;
import lombok.Data;
import java.util.Date;
import java.util.List;

@Builder
@Data
public class OfferDto {
    private Long id;

    private Date bookDate;

    private List<TransportationDto> transportationsDto;

    private List<AccommodationDto> accommodationsDto;

    private CustomerDto customerDto;

    private AgentDto agentDto;
}
