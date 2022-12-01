package com.devlife.profile_service.service;

import com.devlife.profile_service.dto.EducationDto;
import com.devlife.profile_service.entity.Education;
import com.devlife.profile_service.mapper.EducationMapper;
import com.devlife.profile_service.repository.EducationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EducationService {

    private final EducationRepository educationRepository;
    private final EducationMapper mapper;

    public Long addEducation(EducationDto education) {
        Education saveEducation = educationRepository.save(mapper.convertToEntity(education));
        if (saveEducation != null) {
            return saveEducation.getId();
        }
        return null;
    }

    public EducationDto getEducation(Long id) {
        Education education = educationRepository.getById(id);
        return mapper.convertToDto(education);
    }

    public List<EducationDto> getAllEducations() {
        List<Education> educationsList = educationRepository.findAll();
        return educationsList.stream().map(mapper::convertToDto).collect(Collectors.toList());
    }

    public Boolean deleteEducationById(Long id) {
        educationRepository.deleteById(id);
        return !educationRepository.existsById(id);
    }

}
