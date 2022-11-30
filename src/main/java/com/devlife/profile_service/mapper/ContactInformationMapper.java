package com.devlife.profile_service.mapper;

import com.devlife.profile_service.entity.ContactType;
import com.devlife.profile_service.entity.Profile;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "prf_contact_information")
@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class ContactInformationMapper {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "contact_information_id_seq")
    @SequenceGenerator(name = "contact_information_id_seq", sequenceName = "contact_information_id_seq", allocationSize = 1)
    private Long id;

    @ManyToOne(targetEntity = ContactType.class)
    @JoinColumn(name = "contact_type_id")
    private ContactType contactType;

    @Column(name = "primary")
    private Boolean primary;

    @Column(name = "for_auth")
    private Boolean forAuth;

    @Column(name = "value")
    private String value;

    @ManyToOne(targetEntity = Profile.class)
    @JoinColumn(name = "profile_id")
    private Profile profile;

}
