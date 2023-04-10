package com.java.TravelAgency.entity;

import com.java.TravelAgency.constants.Constants;
import com.java.TravelAgency.validator.EmailMatcher;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Date;


@Data
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity // This tells Hibernate to make a table out of this class
@Table(name = Constants.CUSTOMERS_TABLE)
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name ="username", unique = true)
    private String username;

    @Column(name ="password")
    private String password;

    @Column(name = "first_name")
    @NotNull(message = Constants.NOT_NULL)
    private String firstName;

    @Column(name = "last_name")
    @NotNull(message = Constants.NOT_NULL)
    private String lastName;

    private Integer enabled;

    @Column(name = "email")
    @NotNull(message = Constants.NOT_NULL)
    @EmailMatcher()
    private String email;

    @Column(name = "address")
    private String address;

    @Column(name = "birthDate")
     private Date birthDate;

    @Column(name = "gender")
    private String gender;
}
