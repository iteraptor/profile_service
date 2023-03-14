package com.devlife.profile_service.repository;

import com.devlife.profile_service.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Boolean existsByAuthUserId(Long authUserId);
    Optional<User> findByAuthUserId(Long authId);

}