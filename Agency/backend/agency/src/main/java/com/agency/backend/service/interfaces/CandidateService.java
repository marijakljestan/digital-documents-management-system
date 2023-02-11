package com.agency.backend.service.interfaces;

import com.agency.backend.dto.RegisterCandidateDto;

import java.io.IOException;

public interface CandidateService {

    String register(RegisterCandidateDto candidateDto) throws IOException;
}
