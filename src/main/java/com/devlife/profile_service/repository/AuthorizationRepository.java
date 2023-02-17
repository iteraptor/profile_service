package com.devlife.profile_service.repository;

import com.devlife.profile_service.entity.Authorization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthorizationRepository extends JpaRepository<Authorization, Long> {

    Boolean existsByAuthUserId(Long authUserId);

}