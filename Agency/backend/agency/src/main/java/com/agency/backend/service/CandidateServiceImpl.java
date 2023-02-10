package com.agency.backend.service;

import com.agency.backend.dto.RegisterCandidateDto;
import com.agency.backend.model.Candidate;
import com.agency.backend.repository.CandidateRepository;
import com.agency.backend.service.interfaces.CandidateService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import static com.agency.backend.AgencyApplication.LOGGER_INFO;

@Service
@AllArgsConstructor
public class CandidateServiceImpl implements CandidateService {

    private final CandidateRepository candidateRepository;
    private final ModelMapper modelMapper;

    @Override
    public String register(RegisterCandidateDto candidateDto) {
        LOGGER_INFO.info("CANDIDATE SERVICE: register - start.");
        Candidate candidate = modelMapper.map(candidateDto, Candidate.class);
        LOGGER_INFO.info("CANDIDATE SERVICE: register - saving candidate ...");
        Candidate savedCandidate = candidateRepository.save(candidate);
        return savedCandidate.getId();
    }
}
