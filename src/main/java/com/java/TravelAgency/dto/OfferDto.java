package com.java.TravelAgency.dto;

import com.java.TravelAgency.constants.Constants;
import com.java.TravelAgency.entity.Accommodation;
import com.java.TravelAgency.entity.Agent;
import com.java.TravelAgency.entity.Customer;
import com.java.TravelAgency.entity.Transportation;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OfferDto {
    private Long id;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date bookDate;

    private List<TransportationDto> transportationsDto;


    private List<AccommodationDto> accommodationsDto;

    @NotNull(message = Constants.NOT_NULL)
    private CustomerDto customerDto;

    @NotNull(message = Constants.NOT_NULL)
    private AgentDto agentDto;
}
