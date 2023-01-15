package com.java.TravelAgency.dto;



import com.java.TravelAgency.entity.Offer;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Builder
@Data
public class AccommodationDto {

    private Long id;

    private String name;

    private String type;

    private String address;

    private Date timeStart;

    private Date timeEnd;

    private Double price;

}
