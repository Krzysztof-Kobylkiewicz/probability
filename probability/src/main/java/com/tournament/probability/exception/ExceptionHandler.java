package com.tournament.probability.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@ControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(value = {ApiRequestException.class})
    public ResponseEntity<Object> handleApiRequestException(ApiRequestException e){
        // If the ApiRequestException is caught, then:
        // 1. Create payload containing exception details
        HttpStatus notFound = HttpStatus.NOT_FOUND;

        TournamentTypeNotFoundException tournamentTypeNotFoundException =
                new TournamentTypeNotFoundException(
                e.getMessage(),
                notFound,
                ZonedDateTime.now(ZoneId.systemDefault())
        );
        // 2. Retuern response entity
        return new ResponseEntity<>(tournamentTypeNotFoundException, notFound);
    }
}