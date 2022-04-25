package com.dh.integrador.exceptions;

import com.dh.integrador.controller.TurnoController;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptions {
    private static final Logger logger = Logger.getLogger(TurnoController.class);
    @ExceptionHandler({ResourceNotFoundException.class})
    public ResponseEntity<String> procesarErrorNotFound(ResourceNotFoundException ex){
        logger.error(ex);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}
