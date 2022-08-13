package com.carepay.assignment.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class ApplicationExceptionHandler{

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public Map<String,String> handleInputErrors(ConstraintViolationException ex){
        Map<String, String> map = new HashMap<>();
        ex.getConstraintViolations().forEach(e->{
            map.put(e.getPropertyPath().toString(),e.getMessage());
        });
        return map;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoSuchElementException.class)
    public Map<String,String> handleNoSuchElementException(NoSuchElementException ex){

        Map<String, String> map = Map.of("error",ex.getMessage());
        return map;
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public Map<String,String> handleException(Exception ex){
        ex.printStackTrace();
        Map<String, String> map = Map.of("error",ex.getMessage());
        return map;
    }

}
