package com.devlife.profile_service.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "prf_contact_list")
@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class ContactList {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "contactList_id_seq")
    @SequenceGenerator(name = "contactList_id_seq", sequenceName = "contactList_id_seq", allocationSize = 1)
    private Long id;

    @Column(name = "nick_name")
    private String nickName;

    @ManyToOne(targetEntity = Profile.class)
    @JoinColumn(name = "profile_id")
    private Profile profile;

    @Column(name = "user_id")
    private Long userId;

}
