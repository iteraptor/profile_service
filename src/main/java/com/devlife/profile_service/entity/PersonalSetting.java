package com.devlife.profile_service.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "prf_personal_setting")
@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
@EqualsAndHashCode
public class PersonalSetting {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "personalSetting_id_seq")
    @SequenceGenerator(name = "personalSetting_id_seq", sequenceName = "personalSetting_id_seq", allocationSize = 1)
    private Long id;

    @ManyToOne(targetEntity = Profile.class)
    @EqualsAndHashCode.Exclude
    @JoinColumn(name = "profile_id")
    private Profile profile;

    @Column(name = "external_id")
    private Long externalId;

}
