package com.agency.backend.service;

import com.agency.backend.dto.RegisterCandidateDto;
import com.agency.backend.exception.CandidateAlreadyRegisteredException;
import com.agency.backend.model.Address;
import com.agency.backend.model.Candidate;
import com.agency.backend.repository.AddressRepository;
import com.agency.backend.repository.CandidateRepository;
import com.agency.backend.service.interfaces.CandidateService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import static com.agency.backend.AgencyApplication.LOGGER_INFO;

@Service
@AllArgsConstructor
public class CandidateServiceImpl implements CandidateService {

    private final CandidateRepository candidateRepository;
    private final AddressRepository addressRepository;
    private final ModelMapper modelMapper;

    @Override
    public String register(RegisterCandidateDto candidateDto) {
        LOGGER_INFO.info("CANDIDATE SERVICE: register - start.");

        Address address = modelMapper.map(candidateDto.getAddress(), Address.class);
        Candidate candidate = modelMapper.map(candidateDto, Candidate.class);
        candidate.setAddress(address);
        LOGGER_INFO.info("CANDIDATE SERVICE: register - saving candidate...");
        try {
            candidate = candidateRepository.save(candidate);
        }catch(RuntimeException e) { throw new CandidateAlreadyRegisteredException(); }

        return candidate.getId();
    }
}
