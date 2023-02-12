package com.devlife.profile_service.mapper;

import com.devlife.profile_service.dto.asyncMessageModel.ProjectAsyncModel;
import com.devlife.profile_service.entity.Project;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class ProjectMapper {
    private final ModelMapper mapper;

    public Project convertToEntity(ProjectAsyncModel projectAsyncModel) {
        return mapper.map(projectAsyncModel, Project.class);
    }

    @PostConstruct
    public void setupMapper() {
        mapper.createTypeMap(ProjectAsyncModel.class, Project.class)
                .addMappings(s -> s.skip(Project::setId))
                .setPostConverter(context -> {
                            Project project = context.getDestination();
                            project.setExternalId(context.getSource().getId());
                            return project;
                        }
                );
    }
}
