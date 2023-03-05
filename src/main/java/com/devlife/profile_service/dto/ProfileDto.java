package com.devlife.profile_service.dto;

import com.devlife.profile_service.entity.Project;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class ProfileDto implements Serializable {

    private Long id;
    private UserDto user;
    private AvatarDto avatar;
    private CityDto city;
    private CountryDto country;
    private LocalDate dateOfBirth;
    private String firstName;
    private GenderDto gender;
    private String lastName;
    private String middleName;
    private String nickname;
    private String personalInformation;
    private Set<Project> projects;

}
