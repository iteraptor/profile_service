package com.devlife.profile_service.repository;

import com.devlife.profile_service.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {

    Boolean existsByExternalId(Long externalId);

}
