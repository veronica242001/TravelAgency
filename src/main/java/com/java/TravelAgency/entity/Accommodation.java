package com.java.TravelAgency.entity;


import com.java.TravelAgency.constants.Constants;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
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
@Table(name = Constants.ACCOMMODATIONS_TABLE)
public class Accommodation {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    @NotNull(message = Constants.NOT_NULL)
    private String name;

    @Column(name = "type")
    private String type;

    @Column(name = "address")
    @NotNull(message = Constants.NOT_NULL)
    private String address;

    @Column(name = "timeStart")
    private Date timeStart;

    @Column(name = "timeEnd")
    private Date timeEnd;

    @Column(name = "price")
    @NotNull(message = Constants.NOT_NULL)
    private Double price;

    @ManyToMany
    @JoinTable(
            name = "offers_accommodations",
            joinColumns = {@JoinColumn(name = "offers", referencedColumnName = "id")
            }, inverseJoinColumns = {
            @JoinColumn(name = "accommodations", referencedColumnName = "id")
    })
    private List<Offer> offers;

}
