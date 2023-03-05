package com.devlife.profile_service.dto.asyncMessageModel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProjectUserLinkAsyncModel {
    private ProjectAsyncModel project;
    private UserAsyncModel user;
}
