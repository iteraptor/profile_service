package com.devlife.profile_service.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "prf_avatar")
@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class Avatar {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "avatar_id_seq")
    @SequenceGenerator(name = "avatar_id_seq", sequenceName = "avatar_id_seq", allocationSize = 1)
    private Long id;

    @Column(name = "link")
    private String link;

    @Column(name = "data")
    private String data; //TODO разобраться какой тип использовать

}
