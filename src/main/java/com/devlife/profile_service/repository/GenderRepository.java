package com.devlife.profile_service.repository;

import com.devlife.profile_service.entity.Gender;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenderRepository extends JpaRepository<Gender, Long> {
}