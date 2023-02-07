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
    @NotNull(message = "must not be null")
    private Long externalId;
    private String firstName;
    private String lastName;
    private String middleName;
    @NotBlank(message = "must be fill")
    private String nickname;
    @NotNull(message = "must not be null")
    private Integer contactType;
    @NotBlank(message = "must be fill")
    private String contactValue;
}
