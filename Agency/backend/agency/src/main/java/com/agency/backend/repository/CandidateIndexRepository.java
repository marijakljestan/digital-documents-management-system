package com.agency.backend.repository;

import com.agency.backend.model.CandidateIndexUnit;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface CandidateIndexRepository extends ElasticsearchRepository<CandidateIndexUnit, String> {
}
