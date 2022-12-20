package com.devlife.profile_service.service;

import com.devlife.profile_service.dto.EmployerDto;
import com.devlife.profile_service.entity.Employer;
import com.devlife.profile_service.mapper.EmployerMapper;
import com.devlife.profile_service.repository.EmployerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployerService {

    private final EmployerRepository employerRepository;
    private final EmployerMapper mapper;

    public Long addEmployer(EmployerDto employer) {
        Employer saveEmployer = employerRepository.save(mapper.convertToEntity(employer));
        if (saveEmployer != null) {
            return saveEmployer.getId();
        }
        return null;
    }

    public EmployerDto getEmployer(Long id) {
        Employer employer = employerRepository.getById(id);
        return mapper.convertToDto(employer);
    }

    public List<EmployerDto> getAllEmployers() {
        List<Employer> employersList = employerRepository.findAll();
        return employersList.stream().map(mapper::convertToDto).collect(Collectors.toList());
    }

    public Boolean deleteEmployerById(Long id) {
        employerRepository.deleteById(id);
        return !employerRepository.existsById(id);
    }

}
