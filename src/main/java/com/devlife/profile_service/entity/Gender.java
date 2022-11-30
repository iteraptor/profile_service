package com.devlife.profile_service.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "prf_genders")
@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class Gender {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gender_id_seq")
    @SequenceGenerator(name = "gender_id_seq", sequenceName = "gender_id_seq", allocationSize = 1)
    private Long id;

    @Column(name = "name")
    private String name;

}
