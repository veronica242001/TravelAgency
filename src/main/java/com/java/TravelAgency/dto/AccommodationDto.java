package com.java.TravelAgency.dto;



import com.java.TravelAgency.constants.Constants;
import com.java.TravelAgency.entity.Offer;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import java.util.Date;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccommodationDto {

    private Long id;

    @NotBlank(message = Constants.NOT_NULL)
    private String name;

    @NotBlank(message = Constants.NOT_NULL)
    private String type;

    @NotBlank(message = Constants.NOT_NULL)
    private String address;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date timeStart;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date timeEnd;

    @NumberFormat
    @NotNull(message = Constants.NOT_NULL)
    private Double price;

}
