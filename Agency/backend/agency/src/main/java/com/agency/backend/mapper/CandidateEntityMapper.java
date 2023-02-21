package com.agency.backend.mapper;

import com.agency.backend.dto.RegisterCandidateDto;
import com.agency.backend.model.Address;
import com.agency.backend.model.Candidate;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;

@AllArgsConstructor
public class CandidateEntityMapper {

    private final ModelMapper modelMapper;

    public static Candidate toModel(RegisterCandidateDto candidateDto) {
        Address address = Address.builder()
                .streetName(candidateDto.getStreetName()).streetNumber(candidateDto.getStreetNumber())
                .city(candidateDto.getCity()).country(candidateDto.getCountry())
                .build();
        return  Candidate.builder()
                .firstName(candidateDto.getFirstName()).lastName(candidateDto.getLastName()).degree(candidateDto.getDegree())
                .email(candidateDto.getEmail()).password(candidateDto.getPassword()).phoneNumber(candidateDto.getPhoneNumber())
                .address(address)
                .build();
    }
}
