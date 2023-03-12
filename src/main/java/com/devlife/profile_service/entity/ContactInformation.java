package com.devlife.profile_service.entity;

import com.devlife.profile_service.enums.ContactType;
import com.devlife.profile_service.utils.ContactTypeConverter;
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
public class ContactInformation {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "contactInformation_id_seq")
    @SequenceGenerator(name = "contactInformation_id_seq", sequenceName = "contactInformation_id_seq", allocationSize = 1)
    private Long id;

    @JoinColumn(name = "contact_type")
    @Convert(converter = ContactTypeConverter.class)
    private ContactType contactType;

    @Column(name = "primary_info")
    private Boolean primaryInfo;

    @Column(name = "for_auth")
    private Boolean forAuth;

    @Column(name = "value")
    private String value;

    @ManyToOne(targetEntity = Profile.class)
    @JoinColumn(name = "profile_id", nullable = false, updatable = false, insertable = false)
    private Profile profile;

}
