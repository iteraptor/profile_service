package com.devlife.profile_service.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "prf_country")
@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "country_id_seq")
    @SequenceGenerator(name = "country_id_seq", sequenceName = "country_id_seq", allocationSize = 1)
    private Long id;

    @Column(name = "name")
    private String name;

}
