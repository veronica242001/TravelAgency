package com.java.TravelAgency.dto;


import com.java.TravelAgency.constants.Constants;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.util.Date;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto {

    private Long id;

    @Email
    private String email;

    @Length(min = 4, max = 100)
    private String username;

    @Length(min = 4, max = 100)
    private String password;


    @Length(min = 1, max = 100)
    private String firstName;

    @Length(min = 1, max = 50)
    private String lastName;

    @NotEmpty
    @Length(min = 0, max = 300)
    private String address;



    private Date birthDate;

    private String gender;
}
