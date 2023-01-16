package com.java.TravelAgency.entity;


import com.java.TravelAgency.constants.Constants;
import com.java.TravelAgency.validator.EmailMatcher;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity // This tells Hibernate to make a table out of this class
@Table(name = Constants.AGENCIES_TABLE)
public class Agency {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    @NotNull(message = Constants.NOT_NULL)
    private String name;

    @Column(name = "email")
    @NotNull(message = Constants.NOT_NULL)
    @EmailMatcher()
    private String email;

    @Column(name = "address")
    private String address;


}
