package com.devlife.profile_service.dto;

import com.devlife.profile_service.entity.Project;
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
public class ProfileDto implements Serializable {

    private AvatarDto avatar;
    private CityDto city;
    private CountryDto country;
    private LocalDate dateOfBirth;
    private String firstName;
    private GenderDto gender;
    private Long id;
    private String lastName;
    private String middleName;
    private String nickname;
    private String personalInformation;
    private Set<Project> projects;

}
