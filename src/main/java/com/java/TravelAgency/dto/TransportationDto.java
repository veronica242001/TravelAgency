package com.java.TravelAgency.dto;

import com.java.TravelAgency.entity.Offer;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.Builder;
import lombok.Data;

import java.util.Date;


@Data
@Builder
public class TransportationDto {

    private Long id;

    private String name;

    private String type;

    private String addressTo;

    private String addressFrom;

    private Date timeStart;

    private Date timeEnd;

    private Double price;


}
