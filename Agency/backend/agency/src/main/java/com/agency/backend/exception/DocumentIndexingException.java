package com.agency.backend.exception;

public class DocumentIndexingException extends RuntimeException{

    public DocumentIndexingException(String message) {
        super(message);
    }

    public DocumentIndexingException(String message, Throwable cause) {
        super(message, cause);
    }
}
