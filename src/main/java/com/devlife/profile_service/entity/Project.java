package com.devlife.profile_service.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "prf_project")
@Getter
@Setter
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "project_id_seq")
    @SequenceGenerator(name = "project_id_seq", sequenceName = "project_id_seq", allocationSize = 1)
    private Long id;

    @Column(name = "external_id")
    private Long externalId;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "start_date", columnDefinition = "DATE")
    private LocalDate startDate;

    @Column(name = "end_date", columnDefinition = "DATE")
    private LocalDate endDate;

    @ManyToOne(targetEntity = Employer.class)
    @JoinColumn(name = "employer_id")
    private Employer employer;



    @ManyToMany(targetEntity = Project.class)
    @JoinTable(
            name = "prf_project_profile",
            joinColumns = {@JoinColumn(name = "project_id")},
            inverseJoinColumns = {@JoinColumn(name = "profile_id")}
    )
    private Set<Profile> profiles;
}
