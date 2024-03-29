package com.devlife.profile_service.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "prf_education")
@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class Education {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "education_id_seq")
    @SequenceGenerator(name = "education_id_seq", sequenceName = "education_id_seq", allocationSize = 1)
    private Long id;

    @ManyToOne(targetEntity = Profile.class)
    @JoinColumn(name = "profile_id")
    private Profile profile;

    @Column(name = "start_date", columnDefinition = "DATE")
    private LocalDate startDate;

    @Column(name = "end_date", columnDefinition = "DATE")
    private LocalDate endDate;

    @Column(name = "description")
    private String description;

    @Column(name = "educational_institution")
    private String educationalInstitution;

    @Column(name = "external_id")
    private Long externalId;

}
