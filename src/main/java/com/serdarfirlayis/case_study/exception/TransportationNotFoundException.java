package com.serdarfirlayis.case_study.exception;

public class TransportationNotFoundException extends RuntimeException {
    public TransportationNotFoundException(String message) {
        super(message);
    }
}
