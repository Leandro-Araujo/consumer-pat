package br.com.alelo.consumer.consumerpat.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception ex){
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handleResourceNotFoundException(ResourceNotFoundException ex){
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(TransactionBalanceException.class)
    public ResponseEntity<String> handleTransactionBalanceException(TransactionBalanceException ex){
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
}
