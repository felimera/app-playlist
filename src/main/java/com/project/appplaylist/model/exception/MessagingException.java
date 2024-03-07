package com.project.appplaylist.model.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class MessagingException extends RuntimeException {
    private final String code;
    private final HttpStatus httpStatus;

    public MessagingException(String code, HttpStatus httpStatus, String message) {
        super(message);
        this.code = code;
        this.httpStatus = httpStatus;
    }
}

