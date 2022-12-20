package com.devlife.profile_service.repository;

import com.devlife.profile_service.entity.ContactInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactInformationRepository extends JpaRepository<ContactInformation, Long> {
}