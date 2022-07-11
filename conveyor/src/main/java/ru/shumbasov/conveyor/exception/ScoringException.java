package ru.shumbasov.conveyor.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ScoringException extends RuntimeException{
    private String error;
}
