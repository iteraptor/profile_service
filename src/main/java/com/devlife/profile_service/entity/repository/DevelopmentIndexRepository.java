package com.devlife.profile_service.entity.repository;

import com.devlife.profile_service.entity.DevelopmentIndex;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DevelopmentIndexRepository extends JpaRepository<DevelopmentIndex, Long> {
}