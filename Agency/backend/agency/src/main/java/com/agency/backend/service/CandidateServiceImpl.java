package com.agency.backend.service;

import com.agency.backend.dto.RegisterCandidateDto;
import com.agency.backend.exception.CandidateAlreadyRegisteredException;
import com.agency.backend.mapper.CandidateDataMapper;
import com.agency.backend.model.Candidate;
import com.agency.backend.repository.CandidateRepository;
import com.agency.backend.service.interfaces.CandidateService;
import com.agency.backend.storage.FileSystemStorageService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Path;

import static com.agency.backend.AgencyApplication.LOGGER_INFO;

@Service
@AllArgsConstructor
public class CandidateServiceImpl implements CandidateService {
    private final CandidateRepository candidateRepository;

    private final FileSystemStorageService fileStorageService;

    @Override
    @Transactional
    public String register(RegisterCandidateDto candidateDto) {
        LOGGER_INFO.info("CANDIDATE SERVICE: register - start.");
        Candidate candidate = processCandidateData(candidateDto);
        LOGGER_INFO.info("CANDIDATE SERVICE: register - saving candidate...");
        try {
            return candidateRepository.save(candidate).getId();
        }catch(RuntimeException e) { throw new CandidateAlreadyRegisteredException(); }
    }

    private Candidate processCandidateData(RegisterCandidateDto candidateDto) {
        Candidate candidate = CandidateDataMapper.toModel(candidateDto);
        candidateDto.setId(candidateRepository.save(candidate).getId());
        candidate.setCv(storeCandidateCV(candidateDto));
        candidate.setCoverLetter(storeCandidateCoverLetter(candidateDto));
        return candidate;
    }

    private String storeCandidateCV(RegisterCandidateDto candidateDto) {
        String basePath = new File("./cv/").getAbsolutePath();
        File fileName = new File (basePath, candidateDto.getId());
        Path cvPath = fileStorageService.store(candidateDto.getCv(), fileName.getName());
        return cvPath.toString();
    }

    private String storeCandidateCoverLetter(RegisterCandidateDto candidateDto) {
        String basePath = new File("./coverLetters/").getAbsolutePath();
        File fileName = new File (basePath, candidateDto.getId());
        Path cvPath = fileStorageService.store(candidateDto.getCv(), fileName.getName());
        return cvPath.toString();
    }
}
