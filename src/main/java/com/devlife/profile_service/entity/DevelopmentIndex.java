package com.devlife.profile_service.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "prf_development_index")
@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class DevelopmentIndex {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "developmentIndex_id_seq")
    @SequenceGenerator(name = "developmentIndex_id_seq", sequenceName = "developmentIndex_id_seq", allocationSize = 1)
    private Long id;

    @Column(name = "external_id")
    private Long externalId;

    @Column(name = "value")
    private BigDecimal value;

    @ManyToOne(targetEntity = Profile.class)
    @JoinColumn(name = "profile_id")
    private Profile profile;

}
