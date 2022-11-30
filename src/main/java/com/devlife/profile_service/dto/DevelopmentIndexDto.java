package com.devlife.profile_service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DevelopmentIndexDto implements Serializable {

    private Long externalId;
    private Long id;
    private ProfileDto profile;
    private BigDecimal value;

}
