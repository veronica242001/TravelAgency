package com.java.TravelAgency.dto;

import com.java.TravelAgency.constants.Constants;
import com.java.TravelAgency.entity.Offer;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import java.util.Date;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransportationDto {

    private Long id;

    @NotBlank(message = Constants.NOT_NULL)
    private String name;

    @NotBlank(message = Constants.NOT_NULL)
    private String type;

    @NotBlank(message = Constants.NOT_NULL)
    private String addressTo;

    @NotBlank(message = Constants.NOT_NULL)
    private String addressFrom;

    @DateTimeFormat(pattern ="yyyy-MM-dd")
    private Date timeStart;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date timeEnd;

    @NumberFormat
    @NotNull(message = Constants.NOT_NULL)
    private Double price;


}
