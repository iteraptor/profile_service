package com.devlife.profile_service.service;

import com.devlife.profile_service.dto.AuthorizationDto;
import com.devlife.profile_service.entity.Authorization;
import com.devlife.profile_service.mapper.AuthorizationMapper;
import com.devlife.profile_service.repository.AuthorizationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthorizationService {

    private final AuthorizationRepository authorizationRepository;
    private final AuthorizationMapper mapper;

    public Long addAuthorization(AuthorizationDto authorization) {
        Authorization saveAuthorization = authorizationRepository.save(mapper.convertToEntity(authorization));
        if (saveAuthorization != null) {
            return saveAuthorization.getId();
        }
        return null;
    }

    public AuthorizationDto getAuthorization(Long id) {
        Authorization authorization = authorizationRepository.getById(id);
        return mapper.convertToDto(authorization);
    }

    public List<AuthorizationDto> getAllAuthorizations() {
        List<Authorization> authorizationsList = authorizationRepository.findAll();
        return authorizationsList.stream().map(mapper::convertToDto).collect(Collectors.toList());
    }

    public Boolean deleteAuthorizationById(Long id) {
        authorizationRepository.deleteById(id);
        return !authorizationRepository.existsById(id);
    }

}
