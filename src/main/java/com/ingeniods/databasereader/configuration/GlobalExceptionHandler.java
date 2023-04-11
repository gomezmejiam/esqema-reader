package com.ingeniods.databasereader.configuration;

import com.ingeniods.databasereader.shared.exception.DatabaseReaderException;
import com.ingeniods.databasereader.shared.exception.EsquemaInfoException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DatabaseReaderException.class)
    public ResponseEntity<String> handleDatabaseReaderException(DatabaseReaderException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EsquemaInfoException.class)
    public ResponseEntity<String> handleEsquemaInfoException(EsquemaInfoException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGeneralException(Exception ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
