package com.devlife.profile_service.mapper;

import com.devlife.profile_service.entity.Profile;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "prf_personal_settings")
@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class PersonalSettingMapper {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "personalSettings_id_seq")
    @SequenceGenerator(name = "personalSettings_id_seq", sequenceName = "personalSettings_id_seq", allocationSize = 1)
    private Long id;

    @ManyToOne(targetEntity = Profile.class)
    @JoinColumn(name = "profile_id")
    private Profile profile;

    @Column(name = "external_id")
    private Long externalId;

}
