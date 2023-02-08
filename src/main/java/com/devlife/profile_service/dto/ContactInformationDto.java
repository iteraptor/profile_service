package com.devlife.profile_service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContactInformationDto implements Serializable {

    private Boolean primaryInfo;
    private Boolean forAuth;
    private Long id;
    private ProfileDto profile;
    private String value;

}
