package com.devlife.profile_service.mapper;

import com.devlife.profile_service.entity.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "prf_profile")
@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class ProfileMapper {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "profile_id_seq")
    @SequenceGenerator(name = "profile_id_seq", sequenceName = "profile_id_seq", allocationSize = 1)
    private Long id;

    @ManyToOne(targetEntity = Authorization.class)
    @JoinColumn(name = "auth_user_id")
    private Authorization authUser;

    @Column(name = "nick_name")
    private String nickName;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "middle_name")
    private String middleName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "date_of_birth", columnDefinition = "DATE")
    private LocalDate dateOfBirth;

    @ManyToOne(targetEntity = com.devlife.profile_service.entity.Gender.class)
    @JoinColumn(name = "gender_id")
    private Gender Gender;

    @ManyToOne(targetEntity = Country.class)
    @JoinColumn(name = "country_id")
    private Country country;

    @ManyToOne(targetEntity = City.class)
    @JoinColumn(name = "city_id")
    private City city;

    @ManyToOne(targetEntity = Avatar.class)
    @JoinColumn(name = "avatar_id")
    private Avatar avatar;

    @ManyToOne(targetEntity = Employer.class)
    @JoinColumn(name = "current_employer_id")
    private Employer currentEmployer;

    @Column(name = "personal_information")
    private String personalInformation;

}
