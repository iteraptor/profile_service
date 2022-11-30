package com.devlife.profile_service.entity.repository;

import com.devlife.profile_service.entity.PersonalSetting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonalSettingRepository extends JpaRepository<PersonalSetting, Long> {
}