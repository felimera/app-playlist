package com.project.appplaylist.exception;

import com.project.appplaylist.model.exception.KeyValueExceptionsMessage;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.List;

@Getter
@Setter
public class ResponseMessageException extends RuntimeException {
    private final String code;
    private final String message;
    private final List<KeyValueExceptionsMessage> keyValueExceptionsMessages;
    private final HttpStatus httpStatus;

    public ResponseMessageException(String code, String message, List<KeyValueExceptionsMessage> keyValueExceptionsMessages, HttpStatus httpStatus) {
        this.code = code;
        this.message = message;
        this.keyValueExceptionsMessages = keyValueExceptionsMessages;
        this.httpStatus = httpStatus;
    }
}
