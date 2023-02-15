package com.devlife.profile_service.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "prf_personal_setting")
@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class PersonalSetting {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "personalSetting_id_seq")
    @SequenceGenerator(name = "personalSetting_id_seq", sequenceName = "personalSetting_id_seq", allocationSize = 1)
    private Long id;

    @ManyToOne(targetEntity = Profile.class)
    @JoinColumn(name = "profile_id")
    private Profile profile;

    @Column(name = "external_id")
    private Long externalId;

}
