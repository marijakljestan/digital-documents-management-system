package com.agency.backend.service.interfaces;

import com.agency.backend.model.CandidateIndexUnit;

public interface CandidateIndexingService {

    CandidateIndexUnit addCandidate(CandidateIndexUnit indexUnit);
    CandidateIndexUnit updateCandidate(CandidateIndexUnit indexUnit);
    void deleteCandidate(CandidateIndexUnit indexUnit);
}
