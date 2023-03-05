package com.devlife.profile_service.entity;

import lombok.*;

import javax.persistence.*;
import java.time.OffsetDateTime;

@Entity
@Table(name = "prf_user")
@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "auth_id_seq")
    @SequenceGenerator(name = "auth_id_seq", sequenceName = "auth_id_seq", allocationSize = 1)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(name = "auth_user_id")
    @EqualsAndHashCode.Include
    private Long authUserId;

    @Column(name = "date_of_registration", columnDefinition = "DATE")
    @EqualsAndHashCode.Include
    private OffsetDateTime dateOfRegistration;

    @OneToOne(targetEntity = Profile.class, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "profile_id", referencedColumnName = "id")
    @EqualsAndHashCode.Include
    private Profile profile;

}
