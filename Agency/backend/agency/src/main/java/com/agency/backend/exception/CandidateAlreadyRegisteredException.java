package com.agency.backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Candidate with this email already registered.")
public class CandidateAlreadyRegisteredException extends RuntimeException{
}
