package com.devlife.profile_service.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "prf_profile")
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "profile_id_seq")
    @SequenceGenerator(name = "profile_id_seq", sequenceName = "profile_id_seq", allocationSize = 1)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(name = "nickname")
    @EqualsAndHashCode.Include
    private String nickname;

    @Column(name = "first_name")
    @EqualsAndHashCode.Include
    private String firstName;

    @Column(name = "middle_name")
    @EqualsAndHashCode.Include
    private String middleName;

    @Column(name = "last_name")
    @EqualsAndHashCode.Include
    private String lastName;

    @Column(name = "date_of_birth", columnDefinition = "DATE")
    @EqualsAndHashCode.Include
    private LocalDate dateOfBirth;

    @OneToOne
    @JoinColumn(name = "gender_id")
    @EqualsAndHashCode.Include
    private Gender gender;

    @ManyToOne
    @JoinColumn(name = "country_id")
    @EqualsAndHashCode.Include
    private Country country;

    @ManyToOne
    @JoinColumn(name = "city_id")
    @EqualsAndHashCode.Include
    private City city;

    @ManyToOne
    @JoinColumn(name = "avatar_id")
    @EqualsAndHashCode.Include
    private Avatar avatar; //TODO предполагаю, что аватаров может быть несколько, тогда нужно переделать

    @Column(name = "personal_information")
    @EqualsAndHashCode.Include
    private String personalInformation;

    @ManyToMany(mappedBy = "profiles")
    @EqualsAndHashCode.Exclude
    private Set<Project> projects;

    @ManyToMany(mappedBy = "profiles")
    @EqualsAndHashCode.Exclude
    private Set<Employer> employers;

    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER, mappedBy = "profile")
    @EqualsAndHashCode.Exclude
    private User user;

    @OneToMany(mappedBy = "profile", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @EqualsAndHashCode.Exclude
    private Set<ContactInformation> contactInformation = new HashSet<>();

    public void addContactInform(Set<ContactInformation> contactInformation) {
        this.contactInformation.addAll(contactInformation);
        contactInformation.forEach(i -> i.setProfile(this));
    }
}
