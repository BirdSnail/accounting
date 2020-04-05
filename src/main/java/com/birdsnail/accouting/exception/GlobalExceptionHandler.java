package com.birdsnail.accouting.exception;

import org.apache.shiro.authc.IncorrectCredentialsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.security.InvalidParameterException;

/**
 * @author BirdSnail
 * @date 2020/3/17
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ServiceException.class)
    public ResponseEntity<?> handleServiceException(ServiceException ex) {
        return ResponseEntity.status(ex.getStatusCode())
                .body(ErrorResponse.builder()
                        .statusCode(ex.getStatusCode())
                        .errorMessage(ex.getMessage())
                        .errorType(ex.getErrorType())
                        .errorCode("传入参数不对")
                        .build());
    }

    @ExceptionHandler(IncorrectCredentialsException.class)
    public ResponseEntity<?> handleIncorrectCredentialsException(IncorrectCredentialsException ex){
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
                .body(ErrorResponse.builder()
                        .statusCode(406)
                        .errorMessage(ex.getMessage())
                        .errorType(ServiceException.ErrorType.CLIENT)
                        .errorCode("INCORRECT CREDENTIALS")
                        .build());
    }

    @ExceptionHandler(InvalidParameterException.class)
    public ResponseEntity<?> handleIncorrectCredentialsException(InvalidParameterException ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ErrorResponse.builder()
                        .statusCode(400)
                        .errorMessage(ex.getMessage())
                        .errorType(ServiceException.ErrorType.CLIENT)
                        .errorCode("invalid parameter")
                        .build());
    }

}
