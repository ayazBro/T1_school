package com.T1school.ConsumerService.util;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;
@RestControllerAdvice
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class T1ExceptionHandler {
    @ExceptionHandler(T1Exception.class)
    public ExceptionResponse handleException(T1Exception exception) {
        return new ExceptionResponse("other",exception.getMessage());
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public List<ExceptionResponse> handleValidationException(MethodArgumentNotValidException ex) {
        List<FieldError> fieldErrors = ex.getFieldErrors().stream().toList();
        List<ExceptionResponse>list=new ArrayList<>();
        for(FieldError field:fieldErrors) {
            list.add(new ExceptionResponse(field.getField(),field.getDefaultMessage()));
        }

        return list;
    }
}
