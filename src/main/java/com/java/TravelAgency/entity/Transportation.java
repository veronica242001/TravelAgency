package com.java.TravelAgency.entity;


import constants.Constants;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Data
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity // This tells Hibernate to make a table out of this class
@Table(name = Constants.TRANSPORTATIONS_TABLE)
public class Transportation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    private String type;

    @Column(name = "addressTo")
    private String addressTo;

    @Column(name = "addressFrom")
    private String addressFrom;

    @Column(name = "timeStart")
    private Date timeStart;

    @Column(name = "timeEnd")
    private Date timeEnd;

    @Column (name = "price")
    private Double price;

    @ManyToMany
    @JoinTable(
            name = "offers_transportations",
            joinColumns = { @JoinColumn(name="offers", referencedColumnName = "id")
            }, inverseJoinColumns = {
            @JoinColumn(name = "transportations", referencedColumnName = "id")
    })
    private List<Offer> offers;


}
