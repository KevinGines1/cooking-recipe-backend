package com.recime.coding.challenge.recipe.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.Instant;

import static com.recime.coding.challenge.recipe.RecipeController.X_REQUEST_ID;

@ControllerAdvice
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Object> entityNotFoundException(Exception ex, WebRequest request) {
        ErrorDetails errorDetails = ErrorDetails.builder()
                .timestamp(Instant.now())
                .message(ex.getMessage())
                .details(String.format("Request ID: %s", request.getHeader(X_REQUEST_ID)))
                .build();

        return new ResponseEntity<Object>(errorDetails, HttpStatus.NOT_FOUND);
    }
}
