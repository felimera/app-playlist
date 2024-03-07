package com.project.appplaylist.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BadRequest extends RuntimeException {
    private final String code;

    public BadRequest(String code, String message) {
        super(message);
        this.code = code;
    }
}
