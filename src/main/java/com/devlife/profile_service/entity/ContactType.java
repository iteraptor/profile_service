package com.devlife.profile_service.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "prf_contact_types")
@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class ContactType {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "contactType_id_seq")
    @SequenceGenerator(name = "contactType_id_seq", sequenceName = "contactType_id_seq", allocationSize = 1)
    private Long id;

    @Column(name = "name")
    private String name;

}
