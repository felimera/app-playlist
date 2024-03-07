package com.project.appplaylist.util;

import com.project.appplaylist.model.exception.KeyValueExceptionsMessage;
import org.springframework.validation.BindingResult;

import java.util.List;

public class CadenaUtil {
    private CadenaUtil() {
        throw new IllegalStateException(CadenaUtil.class.toString());
    }

    public static List<KeyValueExceptionsMessage> formatMessage(BindingResult bindingResult) {
        return bindingResult.getFieldErrors()
                .stream()
                .map(err -> KeyValueExceptionsMessage.builder().attributeName(err.getField()).attributeValue(err.getDefaultMessage()).build())
                .toList();
    }

    public static String convertTrueFalse(Boolean value) {
        return value.equals(Boolean.TRUE) ? Constants.T : Constants.F;
    }
}
