package com.netmed.patientmodule.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * GlobalExceptionHandler handles all the custom Exceptions thrown by the services
 *
 * @author Nataraj
 * @created 11/02/2021
 */
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * Handles the PatientDetailsNotFoundException
     *
     * @param ex
     * @param request
     * @return
     */
    @ExceptionHandler(PatientDetailsNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleUserNotFoundException(PatientDetailsNotFoundException ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDetails);
    }

    /**
     * Handles the DuplicatePatientRecordFoundException
     *
     * @param ex
     * @param request
     * @return
     */
    @ExceptionHandler(DuplicatePatientRecordFoundException.class)
    public ResponseEntity<ErrorDetails> handleDuplicateUserException(DuplicatePatientRecordFoundException ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDetails);
    }

    /**
     * Handles the UserNotFoundException
     *
     * @param ex
     * @param request
     * @return
     */
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleUserNotFoundException(UserNotFoundException ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDetails);
    }

    @Override
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Map<String, String> errors = ex.getAllErrors().stream()
                .collect(Collectors.toMap((objectError -> ((FieldError) objectError).getField()), ObjectError::getDefaultMessage));
        return handleExceptionInternal(ex, errors, headers, status, request);
    }
}
