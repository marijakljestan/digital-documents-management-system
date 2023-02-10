package com.agency.backend.service.interfaces;

import com.agency.backend.dto.RegisterCandidateDto;

public interface CandidateService {

    String register(RegisterCandidateDto candidateDto);
}
