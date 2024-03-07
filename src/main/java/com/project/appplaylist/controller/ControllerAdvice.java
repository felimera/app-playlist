package com.project.appplaylist.controller;

import com.project.appplaylist.exception.NotFoundException;
import com.project.appplaylist.exception.ResponseMessageException;
import com.project.appplaylist.model.exception.ExceptionControlMessage;
import com.project.appplaylist.model.exception.ExceptionResponseMessage;
import com.project.appplaylist.model.exception.MessagingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvice {
    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<ExceptionResponseMessage> runtimeExceptionHandler(RuntimeException ex) {
        ExceptionResponseMessage error = ExceptionResponseMessage.builder().code("500").message(ex.getMessage()).build();
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(value = NotFoundException.class)
    public ResponseEntity<ExceptionResponseMessage> notFoundExceptionHandler(NotFoundException ex) {
        ExceptionResponseMessage error = ExceptionResponseMessage.builder().code(ex.getCode()).message(ex.getMessage()).build();
        return new ResponseEntity<>(error, ex.getHttpStatus());
    }

    @ExceptionHandler(value = MessagingException.class)
    public ResponseEntity<ExceptionResponseMessage> messagingExceptionHandler(MessagingException ex) {
        ExceptionResponseMessage error = ExceptionResponseMessage.builder().code(ex.getCode()).message(ex.getMessage()).build();
        return new ResponseEntity<>(error, ex.getHttpStatus());
    }

    @ExceptionHandler(value = ResponseMessageException.class)
    public ResponseEntity<ExceptionControlMessage> responseStatusExceptionHandler(ResponseMessageException ex) {
        ExceptionControlMessage error = ExceptionControlMessage.builder().code(ex.getCode()).message(ex.getMessage()).keyValueExceptionsMessages(ex.getKeyValueExceptionsMessages()).build();
        return new ResponseEntity<>(error, ex.getHttpStatus());
    }

}
