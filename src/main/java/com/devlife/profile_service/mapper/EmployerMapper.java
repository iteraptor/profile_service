package com.devlife.profile_service.mapper;

import com.devlife.profile_service.dto.EmployerDto;
import com.devlife.profile_service.entity.Employer;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmployerMapper {

    private final ModelMapper mapper;
    public Employer convertToEntity(EmployerDto employerDto) {
        return mapper.map(employerDto, Employer.class);
    }
    public EmployerDto convertToDto(Employer employerEntity) {
        return mapper.map(employerEntity, EmployerDto.class);
    }

}
