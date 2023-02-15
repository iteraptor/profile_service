package com.devlife.profile_service.dto.asyncMessageModel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployerAsyncModel implements Serializable {
    private Long id;
    private String name;
    private UserGroupAsyncModel userGroup;
}
