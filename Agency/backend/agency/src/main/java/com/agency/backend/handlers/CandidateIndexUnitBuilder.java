package com.agency.backend.handlers;


import com.agency.backend.model.Candidate;
import com.agency.backend.model.CandidateIndexUnit;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import java.io.File;

@AllArgsConstructor
@ComponentScan("com.agency.backend")
@Component
public class CandidateIndexUnitBuilder {

    private final PDFHandler pdfHandler;

    public CandidateIndexUnit getIndexUnit(Candidate candidate) {

        String cvContent = pdfHandler.getTextContent(new File(candidate.getCv()));
        String coverLetterContent = pdfHandler.getTextContent(new File(candidate.getCoverLetter()));

        return CandidateIndexUnit.builder()
                .firstName(candidate.getFirstName()).lastName(candidate.getLastName()).degree(candidate.getDegree())
                .cvContent(cvContent).coverLetterContent(coverLetterContent)
                .build();
    }
}
