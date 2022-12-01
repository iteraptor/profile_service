package com.devlife.profile_service.service;

import com.devlife.profile_service.dto.AvatarDto;
import com.devlife.profile_service.entity.Avatar;
import com.devlife.profile_service.mapper.AvatarMapper;
import com.devlife.profile_service.repository.AvatarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AvatarService {

    private final AvatarRepository avatarRepository;
    private final AvatarMapper mapper;

    public Long addAvatar(AvatarDto avatar) {
        Avatar saveAvatar = avatarRepository.save(mapper.convertToEntity(avatar));
        if (saveAvatar != null) {
            return saveAvatar.getId();
        }
        return null;
    }

    public AvatarDto getAvatar(Long id) {
        Avatar avatar = avatarRepository.getById(id);
        return mapper.convertToDto(avatar);
    }

    public List<AvatarDto> getAllAvatars() {
        List<Avatar> avatarsList = avatarRepository.findAll();
        return avatarsList.stream().map(mapper::convertToDto).collect(Collectors.toList());
    }

    public Boolean deleteAvatarById(Long id) {
        avatarRepository.deleteById(id);
        return !avatarRepository.existsById(id);
    }

}
