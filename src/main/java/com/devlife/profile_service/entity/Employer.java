package com.devlife.profile_service.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "prf_employer")
@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class Employer {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employer_id_seq")
    @SequenceGenerator(name = "employer_id_seq", sequenceName = "employer_id_seq", allocationSize = 1)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "external_id")
    private Long externalId;

    @ManyToMany
    @JoinTable(
            name = "prf_employer_profile",
            joinColumns = {@JoinColumn(name = "employer_id")},
            inverseJoinColumns = {@JoinColumn(name = "profile_id")}
    )
    private Set<Profile> profiles;
}
