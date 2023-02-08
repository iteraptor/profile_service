package com.devlife.profile_service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProfileDto implements Serializable {

    private AuthorizationDto authUser;
    private AvatarDto avatar;
    private CityDto city;
    private CountryDto country;
    private EmployerDto currentEmployer;
    private LocalDate dateOfBirth;
    private String firstName;
    private GenderDto gender;
    private Long id;
    private String lastName;
    private String middleName;
    private String nickname;
    private String personalInformation;

}
