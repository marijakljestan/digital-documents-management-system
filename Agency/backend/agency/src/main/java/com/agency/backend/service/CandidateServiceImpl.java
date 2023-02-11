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

import javax.print.DocFlavor;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

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
        String fileName = new StringBuilder(candidateDto.getId()).append("_")
                                .append(candidateDto.getFirstName()).append("_").append(candidateDto.getLastName())
                                .append("_cv").append(".pdf").toString();
        String directoryPath = "cv/" + fileName;
        fileStorageService.store(candidateDto.getCv(), directoryPath);
        return directoryPath;
    }

    private String storeCandidateCoverLetter(RegisterCandidateDto candidateDto) {
        String fileName = new StringBuilder(candidateDto.getId()).append("_")
                                    .append(candidateDto.getFirstName()).append("_").append(candidateDto.getLastName())
                                    .append("_cover_letter").append(".pdf").toString();
        String directoryPath = "coverLetters/" + fileName;
        fileStorageService.store(candidateDto.getCoverLetter(), directoryPath);
        return directoryPath;
    }
}
