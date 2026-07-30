package com.StudentManagmentSystem.SMS.exceptions;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.StudentManagmentSystem.SMS.dto.response.ErrorResponseDto;
import com.StudentManagmentSystem.SMS.dto.response.ValidationErrorDto;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InvalidCredentialException.class)
    public ResponseEntity<ErrorResponseDto> invalidCredential(InvalidCredentialException ex, HttpServletRequest request){
        return responseBuilder(ex, request, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UnauthorizeException.class)
    public ResponseEntity<ErrorResponseDto> unauthorize(UnauthorizeException ex, HttpServletRequest request){
        return responseBuilder(ex, request, HttpStatus.UNAUTHORIZED);
    }
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorResponseDto> badRequestError(BadRequestException ex, HttpServletRequest request){
        return responseBuilder(ex, request, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> courseNotFound(EntityNotFoundException ex, HttpServletRequest request){
        return responseBuilder(ex, request, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(ResourceAlreadyExistsException.class)
    public ResponseEntity<ErrorResponseDto> alreadyExists(ResourceAlreadyExistsException ex, HttpServletRequest request){
        return responseBuilder(ex, request, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorResponseDto> dataIntrigityException(DataIntegrityViolationException ex, HttpServletRequest request){
        return responseBuilder(ex, request, HttpStatus.CONFLICT);
    }
    public ResponseEntity<ErrorResponseDto> responseBuilder
    (Exception ex,
     HttpServletRequest request,
     HttpStatus status ){
     ErrorResponseDto response = new ErrorResponseDto();
     response.setTimeStamp(LocalDateTime.now());
     response.setStatus(status.value());
     response.setError(status.getReasonPhrase());
     response.setMessage(ex.getMessage());
     response.setPath(request.getRequestURI());
     return new ResponseEntity<>(response, status);
    }
    
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationErrorDto> validationExceptionHandler
    (MethodArgumentNotValidException ex,
    HttpServletRequest request){
       Map<String, String> errors = new HashMap<>();
       ValidationErrorDto response = new ValidationErrorDto();

       ex.getBindingResult().getFieldErrors().forEach(error -> 
       errors.put(error.getField(), error.getDefaultMessage()));

       response.setStatus(HttpStatus.BAD_REQUEST.value());
       response.setError(HttpStatus.BAD_REQUEST.getReasonPhrase());
       response.setTimeStamp(LocalDateTime.now());
       response.setMessage("Validation failed.");
       response.setPath(request.getRequestURI());
       response.setValidationsError(errors);

       return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
