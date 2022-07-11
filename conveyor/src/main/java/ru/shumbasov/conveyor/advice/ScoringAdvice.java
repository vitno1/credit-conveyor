package ru.shumbasov.conveyor.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.shumbasov.conveyor.exception.ScoringException;

@RestControllerAdvice
public class ScoringAdvice {

    @ExceptionHandler(ScoringException.class)
    public ResponseEntity<String> handlerScoringException(ScoringException scoringException) {
        String message = scoringException.getMessage();
        return new ResponseEntity<String>(scoringException.getError(), HttpStatus.BAD_REQUEST);
    }
}
