package com.java.TravelAgency.controller;

import com.java.TravelAgency.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.SQLIntegrityConstraintViolationException;

@ControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler({AgentNotFoundException.class, AgencyNotFoundException.class, TransportationNotFoundException.class,
            AccommodationNotFoundException.class, OfferNotFoundException.class,SQLIntegrityConstraintViolationException.class})
    public ResponseEntity<String> handleControllerNotFoundExceptions(Exception exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(exception.getMessage());
    }
    @ExceptionHandler({AgentAlreadyExistsException.class, AgencyAlreadyExistsException.class, TransportationAlreadyExistsException.class,
            AccommodationAlreadyExistsException.class})
    public ResponseEntity<String> handleControllerAlreadyExistsExceptions(Exception exception) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(exception.getMessage());
    }
}
