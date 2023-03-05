package com.devlife.profile_service.repository;

import com.devlife.profile_service.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Set;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {
    Boolean existsByNickname(String nickname);

    /**
     * Get set of project by userId
     * @param userId user id
     * @return profile
     */
    @Query("select profile from Profile profile " +
            "inner join User user on profile.id = user.profile.id " +
            "where user.id = :userId")
    Profile getProfileByUserId(@Param("userId") Long userId);


    @Query("select p from Profile p " +
            "join fetch User u on u.profile.id = p.id " +
            "where u.id in :userIds")
    Set<Profile> getProfilesByUserIdIn(@Param("userIds") Collection<Long> userIds);
}