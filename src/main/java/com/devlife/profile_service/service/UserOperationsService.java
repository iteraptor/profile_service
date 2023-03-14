package com.devlife.profile_service.service;

import com.devlife.profile_service.dto.UserDto;
import com.devlife.profile_service.dto.apiRequestDto.InitProfileReq;
import com.devlife.profile_service.entity.ContactInformation;
import com.devlife.profile_service.entity.Profile;
import com.devlife.profile_service.entity.User;
import com.devlife.profile_service.enums.ContactType;
import com.devlife.profile_service.exception.ExternalUserIdAlreadyExistsException;
import com.devlife.profile_service.exception.NicknameAlreadyExistsException;
import com.devlife.profile_service.mapper.ProfileMapper;
import com.devlife.profile_service.mapper.UserMapper;
import com.devlife.profile_service.repository.ProfileRepository;
import com.devlife.profile_service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Log4j2
@Service
public class UserOperationsService {
    private final ProfileRepository profileRepository;
    private final UserRepository userRepository;
    private final ProfileMapper profileMapper;
    private final UserMapper userMapper;

    @Transactional
    public UserDto initUserWithProfileAndContactInfo(InitProfileReq initProfileReq) {
        checkUser(initProfileReq.getExternalId(), initProfileReq.getNickname());

        final Profile profile = profileMapper.convertFromInitProfileReqToEntity(initProfileReq);
        User savedUser = userRepository.saveAndFlush(User.builder()
                .profile(profile)
                .dateOfRegistration(OffsetDateTime.now(ZoneId.of("Z")))
                .authUserId(initProfileReq.getExternalId())
                .build());

        profile.addContactInform(initProfileReq.getContactInfoList().stream()
                .map(i -> ContactInformation.builder()
                        .contactType(ContactType.getByValue(i.getContactType()))
                        .primaryInfo(true)
                        .forAuth(true)
                        .value(i.getContactValue())
                        .build()).collect(Collectors.toSet()));

        return userMapper.convertToDto(savedUser);
    }


    private void checkUser(Long externalId, String nickname) {
        if (profileRepository.existsByNickname(nickname)) {
            throw new NicknameAlreadyExistsException(nickname);
        }
        if (userRepository.existsByAuthUserId(externalId)) {
            throw new ExternalUserIdAlreadyExistsException(externalId);
        }
    }
}
