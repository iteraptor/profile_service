package com.devlife.profile_service.service;

import com.devlife.profile_service.dto.UserDto;
import com.devlife.profile_service.entity.User;
import com.devlife.profile_service.mapper.UserMapper;
import com.devlife.profile_service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper mapper;

    public Long addAuthorization(UserDto authorization) {
        User saveUser = userRepository.save(mapper.convertToEntity(authorization));
        if (saveUser != null) {
            return saveUser.getId();
        }
        return null;
    }

    public UserDto getAuthorization(Long id) {
        User user = userRepository.getById(id);
        return mapper.convertToDto(user);
    }

    public List<UserDto> getAllAuthorizations() {
        List<User> authorizationsList = userRepository.findAll();
        return authorizationsList.stream().map(mapper::convertToDto).collect(Collectors.toList());
    }

    public Boolean deleteAuthorizationById(Long id) {
        userRepository.deleteById(id);
        return !userRepository.existsById(id);
    }

}
