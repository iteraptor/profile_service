package com.devlife.profile_service.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "prf_authorizations")
@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class Authorization {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "auth_id_seq")
    @SequenceGenerator(name = "auth_id_seq", sequenceName = "auth_id_seq", allocationSize = 1)
    private Long id;

    @ManyToOne(targetEntity = Authorization.class)
    @JoinColumn(name = "auth_user_id")
    private Authorization AuthUser;

    @Column(name = "date_of_registration", columnDefinition = "DATE")
    private LocalDate DateOfRegistration;

    @ManyToOne(targetEntity = Profile.class)
    @JoinColumn(name = "profile_id")
    private Profile profile;

}
