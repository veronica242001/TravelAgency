package com.java.TravelAgency.dto;


import com.java.TravelAgency.constants.Constants;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto {

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
    private String address;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthDate;

    @NotBlank(message = Constants.NOT_NULL)
    private String gender;
}
