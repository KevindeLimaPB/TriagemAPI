package org.sistema.triagem.Global;

import org.sistema.triagem.Exception.Conflict;
import org.sistema.triagem.Exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.sistema.triagem.Exception.ErrorResponse;

@RestControllerAdvice
public class GlobalHeaderException {

    //retorna 409
    @ExceptionHandler(Conflict.class)
    public ResponseEntity<ErrorResponse> HandlerExceptionCpf(Conflict ex){
        ErrorResponse error = ErrorResponse.builder()
                .response(ex.getMessage())
                .status(HttpStatus.CONFLICT.value())
                .build();

        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> HandlerNotFound(NotFoundException ex){
        ErrorResponse error = ErrorResponse.builder()
                .response(ex.getMessage())
                .status(HttpStatus.NOT_FOUND.value())
                .build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

}
