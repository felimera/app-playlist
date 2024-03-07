package com.project.appplaylist.model.exception;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class KeyValueExceptionsMessage {
    private final String attributeName;
    private final String attributeValue;
}
