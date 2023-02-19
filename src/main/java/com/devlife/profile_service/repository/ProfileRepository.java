package com.devlife.profile_service.repository;

import com.devlife.profile_service.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {
    Boolean existsByNickname(String nickname);

    /**
     * Get set of project by userId
     * @param userId user id
     * @return profile
     */
    @Query("select profile from Profile profile " +
            "inner join Authorization user on profile.id = user.profile.id " +
            "where user.id = :userId")
    Profile getProfileByUserId(@Param("userId") Long userId);
}