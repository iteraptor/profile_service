package com.devlife.profile_service.service;

import com.devlife.profile_service.dto.GenderDto;
import com.devlife.profile_service.entity.Gender;
import com.devlife.profile_service.mapper.GenderMapper;
import com.devlife.profile_service.repository.GenderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GenderService {

    private final GenderRepository genderRepository;
    private final GenderMapper mapper;

    public Long addGender(GenderDto gender) {
        Gender saveGender = genderRepository.save(mapper.convertToEntity(gender));
        if (saveGender != null) {
            return saveGender.getId();
        }
        return null;
    }

    public GenderDto getGender(Long id) {
        Gender gender = genderRepository.getById(id);
        return mapper.convertToDto(gender);
    }

    public List<GenderDto> getAllGenders() {
        List<Gender> gendersList = genderRepository.findAll();
        return gendersList.stream().map(mapper::convertToDto).collect(Collectors.toList());
    }

    public Boolean deleteGenderById(Long id) {
        genderRepository.deleteById(id);
        return !genderRepository.existsById(id);
    }

}
