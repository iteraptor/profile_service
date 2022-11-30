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
public class EducationDto implements Serializable {

    private String description;
    private String educationalInstitution;
    private LocalDate endDate;
    private Long id;
    private ProfileDto Profile;
    private LocalDate startDate;
    
}
