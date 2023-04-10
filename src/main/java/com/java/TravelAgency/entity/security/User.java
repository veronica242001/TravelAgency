package com.java.TravelAgency.entity.security;

import jakarta.persistence.*;
import lombok.*;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String username;
    private String password;

    @Singular
    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinTable(name = "user_authority", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name="authority_id", referencedColumnName = "id"))
    private Set<Authority> authorities;

    @Builder.Default
    private Boolean enabled = true;

    @Builder.Default
    private Boolean accountNotExpired = true;

    @Builder.Default
    private Boolean accountNotLocked = true;

    @Builder.Default
    private Boolean credentialsNotExpired = true;

}