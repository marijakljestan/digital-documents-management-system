package com.agency.backend.repository;

import com.agency.backend.model.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CandidateRepository extends JpaRepository <Candidate, String> {
}
