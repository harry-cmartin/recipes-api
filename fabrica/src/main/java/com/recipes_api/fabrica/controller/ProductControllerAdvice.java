package com.recipes_api.fabrica.controller;


import com.recipes_api.fabrica.exceptions.NoSuchDescription;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@ControllerAdvice
public class ProductControllerAdvice extends ResponseEntityExceptionHandler {


    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity handleNoSuchElementException(NoSuchElementException e) {

        Map<String, Object> errors = new HashMap<>();

        errors.put("message: ", "Possivel valor nulo ou vazio: " + e.getMessage() );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }

    @ExceptionHandler(NoSuchDescription.class)
    public ResponseEntity handleNoSuchDescription(NoSuchDescription e) {

        Map<String, Object> errors = new HashMap<>();

        errors.put("message: ", "Possivel valor DE DESCRICAO nulo ou vazio: " + e.getMessage() );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }

}
