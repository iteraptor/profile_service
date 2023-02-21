package com.devlife.profile_service.dto.apiRequestDto;

import com.devlife.profile_service.dto.AvatarDto;
import com.devlife.profile_service.dto.CityDto;
import com.devlife.profile_service.dto.CountryDto;
import com.devlife.profile_service.dto.GenderDto;
import com.devlife.profile_service.entity.Project;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateProfileByProfileIdReq {

    private String firstName;
    private String middleName;
    private String lastName;
    private String personalInformation;
    private LocalDate dateOfBirth;
    private Long countryId;
    private Long cityId;
    private Long avatarId;
    private Long genderId;

}
