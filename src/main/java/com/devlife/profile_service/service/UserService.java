package com.devlife.profile_service.service;

import com.devlife.profile_service.dto.UserDto;
import com.devlife.profile_service.entity.User;
import com.devlife.profile_service.exception.UserNotFoundException;
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

    public Long addUser(UserDto User) {
        User saveUser = userRepository.save(mapper.convertToEntity(User));
        if (saveUser != null) {
            return saveUser.getId();
        }
        return null;
    }

    public UserDto getUser(Long id) {
        User user = userRepository.getById(id);
        return mapper.convertToDto(user);
    }

    public List<UserDto> getAllUsers() {
        List<User> UsersList = userRepository.findAll();
        return UsersList.stream().map(mapper::convertToDto).collect(Collectors.toList());
    }

    public Boolean deleteUserById(Long id) {
        userRepository.deleteById(id);
        return !userRepository.existsById(id);
    }

    public Long getUserIdByAuthId(Long authId) {
        return userRepository.findByAuthUserId(authId).map(User::getId)
                .orElseThrow(() -> new UserNotFoundException("authUserId", authId));
    }

}
