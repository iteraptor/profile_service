package com.devlife.profile_service.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.OffsetDateTime;

@Entity
@Table(name = "prf_authorization")
@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class Authorization {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "auth_id_seq")
    @SequenceGenerator(name = "auth_id_seq", sequenceName = "auth_id_seq", allocationSize = 1)
    private Long id;

    @Column(name = "auth_user_id")
    private Long authUserId;

    @Column(name = "date_of_registration", columnDefinition = "DATE")
    private OffsetDateTime dateOfRegistration;

    @ManyToOne(targetEntity = Profile.class)
    @JoinColumn(name = "profile_id")
    private Profile profile;

}
