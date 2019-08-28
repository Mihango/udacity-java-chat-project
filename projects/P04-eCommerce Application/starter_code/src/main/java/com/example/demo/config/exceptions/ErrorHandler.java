package com.example.demo.config.exceptions;

import com.example.demo.utils.Error;
import lombok.extern.log4j.Log4j2;
import org.h2.jdbc.JdbcSQLIntegrityConstraintViolationException;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.transaction.CannotCreateTransactionException;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Log4j2
@ControllerAdvice
public class ErrorHandler {

    @SuppressWarnings("unchecked")
    @ExceptionHandler({AuthenticationException.class, AccessDeniedException.class})
    public ResponseEntity<?> processAccessDenied(AuthenticationException ex) {
        // log.error("Access Denied exception thrown", ex);
        Error response = new Error();
        response.setMessage("Sorry you don't have privileges to perform this action");
        // response.setData(new CustomEntry("description", ex.getMessage()));
        log.error("Access Denied: Sorry you don't have privileges to perform this action", ex);
        return new ResponseEntity(response, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler({JdbcSQLIntegrityConstraintViolationException.class, DataIntegrityViolationException.class, ConstraintViolationException.class})
    public ResponseEntity<?> processGeneralException(Exception ex) {
        log.error("Creation Error: Data is not unique", ex);
        Error response = new Error();
        response.setMessage("Data is not unique ");
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
