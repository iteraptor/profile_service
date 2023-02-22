package com.devlife.profile_service.dto.apiRequestDto;

import io.swagger.v3.oas.annotations.Parameter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateProfileByProfileIdReq {

    @Parameter(description = "user first name", name = "firstName")
    @Size(max = 255, message = "A first name must not be longer than 255 characters.")
    private String firstName;
    @Parameter(description = "user middle name", name = "middleName")
    @Size(max = 255, message = "A middle name must not be longer than 255 characters.")
    private String middleName;
    @Parameter(description = "user last name", name = "lastName")
    @Size(max = 255, message = "A last name must not be longer than 255 characters.")
    private String lastName;
    @Parameter(description = "user personal information", name = "personalInformation")
    @Size(max = 255, message = "A personal information must not be longer than 255 characters.")
    private String personalInformation;
    @Parameter(description = "user date of birth", name = "dateOfBirth")
    private LocalDate dateOfBirth;
    @Parameter(description = "user country id", name = "countryId")
    private Long countryId;
    @Parameter(description = "user city id", name = "cityId")
    private Long cityId;
    @Parameter(description = "user avatar id", name = "avatarId")
    private Long avatarId;
    @Parameter(description = "user gender id", name = "genderId")
    private Long genderId;

}
