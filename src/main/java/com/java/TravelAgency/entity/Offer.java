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
@Table(name = Constants.OFFERS_TABLE)
public class Offer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "bookDate")
    private Date bookDate;

    @ManyToMany
    @JoinTable(
            name = "offers_transportations",
            joinColumns = {@JoinColumn(name = "transportations", referencedColumnName = "id")
            }, inverseJoinColumns = {
            @JoinColumn(name = "offers", referencedColumnName = "id")
    })
    private List<Transportation> transportations;

    @ManyToMany
    @JoinTable(
            name = "offers_accommodations",
            joinColumns = {@JoinColumn(name = "accommodations", referencedColumnName = "id")
            }, inverseJoinColumns = {
            @JoinColumn(name = "offers", referencedColumnName = "id")
    })
    private List<Accommodation> accommodations;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinTable(name = "customers_offers",
            joinColumns = {@JoinColumn(name = "customers", referencedColumnName = "id")
            }, inverseJoinColumns = {
            @JoinColumn(name = "offers", referencedColumnName = "id")
    })
    private Customer customer;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinTable(name = "agents_offers",
            joinColumns = {@JoinColumn(name = "agents", referencedColumnName = "id")
            }, inverseJoinColumns = {
            @JoinColumn(name = "offers", referencedColumnName = "id")
    })
    private Agent agent;
}
