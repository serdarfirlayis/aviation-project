package com.serdarfirlayis.case_study.exception;

import com.serdarfirlayis.case_study.model.GenericResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(LocationNotFoundException.class)
    public ResponseEntity<GenericResponse<String>> handleUserNotFoundException(LocationNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(GenericResponse.error(ex.getMessage()));
    }

    @ExceptionHandler(TransportationNotFoundException.class)
    public ResponseEntity<GenericResponse<String>> handleUserNotFoundException(TransportationNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(GenericResponse.error(ex.getMessage()));
    }
}
