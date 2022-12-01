package com.devlife.profile_service.service;

import com.devlife.profile_service.dto.DevelopmentIndexDto;
import com.devlife.profile_service.entity.DevelopmentIndex;
import com.devlife.profile_service.mapper.DevelopmentIndexMapper;
import com.devlife.profile_service.repository.DevelopmentIndexRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DevelopmentIndexService {

    private final DevelopmentIndexRepository developmentIndexRepository;
    private final DevelopmentIndexMapper mapper;

    public Long addDevelopmentIndex(DevelopmentIndexDto developmentIndex) {
        DevelopmentIndex saveDevelopmentIndex = developmentIndexRepository.save(mapper.convertToEntity(developmentIndex));
        if (saveDevelopmentIndex != null) {
            return saveDevelopmentIndex.getId();
        }
        return null;
    }

    public DevelopmentIndexDto getDevelopmentIndex(Long id) {
        DevelopmentIndex developmentIndex = developmentIndexRepository.getById(id);
        return mapper.convertToDto(developmentIndex);
    }

    public List<DevelopmentIndexDto> getAllDevelopmentIndexes() {
        List<DevelopmentIndex> developmentIndexesList = developmentIndexRepository.findAll();
        return developmentIndexesList.stream().map(mapper::convertToDto).collect(Collectors.toList());
    }

    public Boolean deleteDevelopmentIndexById(Long id) {
        developmentIndexRepository.deleteById(id);
        return !developmentIndexRepository.existsById(id);
    }

}
