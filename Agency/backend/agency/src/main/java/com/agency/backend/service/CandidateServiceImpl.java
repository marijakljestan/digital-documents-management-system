package com.agency.backend.service;

import com.agency.backend.dto.RegisterCandidateDto;
import com.agency.backend.exception.CandidateAlreadyRegisteredException;
import com.agency.backend.handlers.CandidateIndexUnitBuilder;
import com.agency.backend.mapper.CandidateEntityMapper;
import com.agency.backend.model.Candidate;
import com.agency.backend.model.CandidateIndexUnit;
import com.agency.backend.repository.CandidateRepository;
import com.agency.backend.service.interfaces.CandidateIndexingService;
import com.agency.backend.service.interfaces.CandidateService;
import com.agency.backend.storage.FileSystemStorageService;
import javax.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.UUID;

import static com.agency.backend.AgencyApplication.LOGGER_INFO;

@Service
@AllArgsConstructor
public class CandidateServiceImpl implements CandidateService {

    private final CandidateRepository candidateRepository;
    private final FileSystemStorageService fileStorageService;
    private final CandidateIndexUnitBuilder candidateIndexBuilder;
    private final CandidateIndexingService candidateIndexingService;

    @Override
    @Transactional
    public String register(RegisterCandidateDto candidateDto) {
        LOGGER_INFO.info("CANDIDATE SERVICE: register - start.");
        Candidate candidate = processCandidateData(candidateDto);
        LOGGER_INFO.info("CANDIDATE SERVICE: indexing candidate...");
        CandidateIndexUnit candidateIndexUnit = candidateIndexBuilder.getIndexUnit(candidate);
        candidateIndexingService.addCandidate(candidateIndexUnit);
        LOGGER_INFO.info("CANDIDATE SERVICE: register - saving candidate...");
        try {
            return candidateRepository.save(candidate).getId();
        }catch(RuntimeException e) { throw new CandidateAlreadyRegisteredException(); }
    }

    private Candidate processCandidateData(RegisterCandidateDto candidateDto) {
        LOGGER_INFO.info("CANDIDATE SERVICE: processCandidateData - start.");
        Candidate candidate = CandidateEntityMapper.toModel(candidateDto);
        candidateDto.setId(UUID.randomUUID().toString());
        candidate.setCv(storeCandidateCV(candidateDto));
        candidate.setCoverLetter(storeCandidateCoverLetter(candidateDto));
        LOGGER_INFO.info("CANDIDATE SERVICE: processCandidateData - end.");
        return candidate;
    }

    private String storeCandidateCV(RegisterCandidateDto candidateDto) {
        LOGGER_INFO.info("CANDIDATE SERVICE: storeCandidateCV - start.");
        String fileName = new StringBuilder(candidateDto.getId()).append("_")
                                .append(candidateDto.getFirstName()).append("_").append(candidateDto.getLastName())
                                .append("_cv").append(".pdf").toString();
        String directoryPath = "cv"  + File.separator + fileName;
        String ret = fileStorageService.store(candidateDto.getCv(), directoryPath);
        LOGGER_INFO.info("CANDIDATE SERVICE: storeCandidateCV - end.");
        return ret;
    }

    private String storeCandidateCoverLetter(RegisterCandidateDto candidateDto) {
        LOGGER_INFO.info("CANDIDATE SERVICE: storeCandidateCoverLetter - start.");
        String fileName = new StringBuilder(candidateDto.getId()).append("_")
                                    .append(candidateDto.getFirstName()).append("_").append(candidateDto.getLastName())
                                    .append("_cover_letter").append(".pdf").toString();
        String directoryPath = "coverLetters"  + File.separator + fileName;
        String ret = fileStorageService.store(candidateDto.getCoverLetter(), directoryPath);
        LOGGER_INFO.info("CANDIDATE SERVICE: storeCandidateCoverLetter - end.");
        return ret;
    }
}
