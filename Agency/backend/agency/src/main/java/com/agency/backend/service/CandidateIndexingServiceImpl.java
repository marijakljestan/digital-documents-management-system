package com.agency.backend.service;

import com.agency.backend.model.CandidateIndexUnit;
import com.agency.backend.repository.CandidateIndexRepository;
import com.agency.backend.service.interfaces.CandidateIndexingService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CandidateIndexingServiceImpl implements CandidateIndexingService {

    private final CandidateIndexRepository candidateIndexRepository;

    @Override
    public CandidateIndexUnit addCandidate(CandidateIndexUnit indexUnit) {
        return candidateIndexRepository.save(indexUnit);
    }

    @Override
    public CandidateIndexUnit updateCandidate(CandidateIndexUnit indexUnit) {
        return this.candidateIndexRepository.save(indexUnit);
    }

    @Override
    public void deleteCandidate(CandidateIndexUnit indexUnit) {
        this.candidateIndexRepository.delete(indexUnit);
    }
}
