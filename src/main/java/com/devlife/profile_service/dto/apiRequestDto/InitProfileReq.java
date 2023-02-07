package com.devlife.profile_service.dto.apiRequestDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InitProfileReq {
    @NotNull
    private Long externalId;
    private String firstName;
    private String lastName;
    private String middleName;
    @NotBlank
    private String nickname;
    @NotNull
    private Integer contactType;
    @NotBlank
    private String contactValue;
}
