package com.coderhack.coderhack.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> resourseNotFoundException(ResourceNotFoundException rs){
        return new ResponseEntity<String>(rs.getMessage(),HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> methodArgumentNotValidException(MethodArgumentNotValidException ex){
        Map<String,String> mp=new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(err->{
            String name=((FieldError)err).getField();
            String mess=err.getDefaultMessage();
            mp.put(name, mess);
        });
        return new ResponseEntity<>(mp,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceAlreadyExistException.class)
    public ResponseEntity<String> resourceAlreadyExistException(ResourceAlreadyExistException rs){
        return new ResponseEntity<String>(rs.getMessage(),HttpStatus.BAD_REQUEST);
    }
}
