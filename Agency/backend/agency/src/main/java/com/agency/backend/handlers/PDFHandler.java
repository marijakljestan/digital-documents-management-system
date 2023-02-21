package com.agency.backend.handlers;

import com.agency.backend.exception.DocumentParsingException;;
import org.apache.pdfbox.io.RandomAccessFile;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;

import static com.agency.backend.AgencyApplication.LOGGER_ERROR;

@Component
public class PDFHandler extends DocumentHandler {

    @Override
    public String getTextContent(File file) {
        try {
            LOGGER_ERROR.error("PDF HANDLER: getTextContent - start.");
            PDFParser parser = new PDFParser(new RandomAccessFile(file, "r"));
            parser.parse();
            PDFTextStripper textStripper = new PDFTextStripper();
            String text = textStripper.getText(parser.getPDDocument());
            LOGGER_ERROR.error("PDF HANDLER: getTextContent - end.");
            return text;
        } catch (IOException e) {
            LOGGER_ERROR.error("PDF HANDLER: getTextContent " + e.getMessage());
            throw new DocumentParsingException("Error parsing pdf document", e);
        }
    }

    public String getTextContent(PDFParser parser) {
        try {
            LOGGER_ERROR.error("PDF HANDLER: getTextContent - start.");
            PDFTextStripper textStripper = new PDFTextStripper();
            String text = textStripper.getText(parser.getPDDocument());
            LOGGER_ERROR.error("PDF HANDLER: getTextContent - end.");
            return text;
        } catch (IOException e) {
            LOGGER_ERROR.error("PDF HANDLER: getTextContent " + e.getMessage());
            throw new DocumentParsingException("Error parsing pdf document", e);
        }
    }
}
