package com.devlife.profile_service.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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

}
