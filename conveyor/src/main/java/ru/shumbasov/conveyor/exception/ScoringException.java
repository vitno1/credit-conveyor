package ru.shumbasov.conveyor.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
@Data
@AllArgsConstructor
public class ScoringException extends RuntimeException{
    private String error;


}
