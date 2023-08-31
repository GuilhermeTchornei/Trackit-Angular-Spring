package com.trackit.api.error;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import io.jsonwebtoken.SignatureException;

@ControllerAdvice
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({ AccessDeniedException.class })
    ResponseEntity<Object> handleAccessDeniedException(Exception ex, WebRequest request) {
        return new ResponseEntity<Object>("Errorrrrr", new HttpHeaders(), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler({ Exception.class })
    ResponseEntity<Object> handleOtherExceptions(Exception ex, WebRequest request) {
        return new ResponseEntity<Object>("testeeeee", HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler({ SignatureException.class })
    ResponseEntity<Object> handleSignatureException(Exception ex, WebRequest request) {
        return new ResponseEntity<Object>("testeeeee", HttpStatus.UNAUTHORIZED);
    }

}
