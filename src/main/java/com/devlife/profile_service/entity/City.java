package com.devlife.profile_service.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "prf_cities")
@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "city_id_seq")
    @SequenceGenerator(name = "city_id_seq", sequenceName = "city_id_seq", allocationSize = 1)
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToOne(targetEntity = Country.class)
    @JoinColumn(name = "country_id")
    private Country country;

}
