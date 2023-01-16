package com.java.TravelAgency.entity;

import constants.Constants;
import jakarta.persistence.*;
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
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "phone")
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
