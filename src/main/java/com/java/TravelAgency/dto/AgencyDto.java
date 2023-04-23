package com.java.TravelAgency.dto;


import com.java.TravelAgency.constants.Constants;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AgencyDto {

    private Long id;

    @NotBlank(message = Constants.NOT_NULL)
    private String name;

    @Email(message = Constants.EMAIL_MATCH)
    @NotBlank(message = Constants.NOT_NULL)
    private String email;

    @NotBlank(message = Constants.NOT_NULL)
    private String address;

}
