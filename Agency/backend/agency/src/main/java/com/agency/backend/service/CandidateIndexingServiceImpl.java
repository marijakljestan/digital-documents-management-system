package com.agency.backend.service;

import com.agency.backend.model.CandidateIndexUnit;
import com.agency.backend.repository.CandidateIndexRepository;
import com.agency.backend.service.interfaces.CandidateIndexingService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import static com.agency.backend.AgencyApplication.LOGGER_INFO;

@Service
@AllArgsConstructor
public class CandidateIndexingServiceImpl implements CandidateIndexingService {

    private final CandidateIndexRepository candidateIndexRepository;

    @Override
    public CandidateIndexUnit addCandidate(CandidateIndexUnit indexUnit) {
        LOGGER_INFO.info("CANDIDATE INDEXING SERVICE: addCandidate - saving candidate...");
        return candidateIndexRepository.save(indexUnit);
    }

    @Override
    public CandidateIndexUnit updateCandidate(CandidateIndexUnit indexUnit) {
        LOGGER_INFO.info("CANDIDATE INDEXING SERVICE: updateCandidate - updating candidate...");
        return this.candidateIndexRepository.save(indexUnit);
    }

    @Override
    public void deleteCandidate(CandidateIndexUnit indexUnit) {
        LOGGER_INFO.info("CANDIDATE INDEXING SERVICE: deleteCandidate - deleting candidate...");
        this.candidateIndexRepository.delete(indexUnit);
    }
}
