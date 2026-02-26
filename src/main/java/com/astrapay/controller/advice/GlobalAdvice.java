package com.astrapay.controller.advice;

import com.astrapay.dto.response.BaseResponses;
import com.astrapay.exception.NotesException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class GlobalAdvice {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<BaseResponses<String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        return new ResponseEntity<>(
                new BaseResponses<>(
                        false,
                        ex.getBindingResult().getAllErrors().get(0).getDefaultMessage()
                ),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(NotesException.class)
    public ResponseEntity<BaseResponses<String>> handleNoteNotFound(NotesException ex) {
        return new ResponseEntity<>(
                new BaseResponses<>(
                        false,
                        ex.getMessage()
                ),
                HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<BaseResponses<String>> handleGeneric(Exception ex) {
        return new ResponseEntity<>(
                new BaseResponses<>(
                        false,
                        ex.getMessage()
                ),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }
}