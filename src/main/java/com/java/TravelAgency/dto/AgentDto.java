package com.java.TravelAgency.dto;


import com.java.TravelAgency.constants.Constants;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.NumberFormat;

@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AgentDto {

    private Long id;

    @Email(message = Constants.EMAIL_MATCH)
    @NotBlank(message = Constants.NOT_NULL)
    private String email;

    @NotBlank(message = Constants.NOT_NULL)
    private String username;

    @NotBlank(message = Constants.NOT_NULL)
    private String password;

    @NotBlank(message = Constants.NOT_NULL)
    private String firstName;

    @NotBlank(message = Constants.NOT_NULL)
    private String lastName;

    @NotBlank(message = Constants.NOT_NULL)
    private String phone;

    @NumberFormat
    @NotNull(message = Constants.NOT_NULL)
    private Double salary;

    private AgencyDto agencyDto;

}
