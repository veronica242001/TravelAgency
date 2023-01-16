package com.java.TravelAgency.entity;

import com.java.TravelAgency.constants.Constants;
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
@Table(name = Constants.AGENTS_TABLE)
public class Agent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "first_name")
    @NotNull(message = Constants.NOT_NULL)
    private String firstName;

    @Column(name = "last_name")
    @NotNull(message = Constants.NOT_NULL)
    private String lastName;

    @Column(name = "phone")
    @NotNull(message = Constants.NOT_NULL)
    private String phone;

    @Column(name = "salary")
    private Double salary;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinTable(name = "agents_agencies",
            joinColumns = {@JoinColumn(name = "agencies", referencedColumnName = "id")
            }, inverseJoinColumns = {
            @JoinColumn(name = "agents", referencedColumnName = "id")
    })
    private Agency agency;


}
