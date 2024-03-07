package com.project.appplaylist.util;

public class Constants {
    private Constants() {
        throw new IllegalStateException(Constants.class.toString());
    }

    public static final String AUTHORIZATION = "Authorization";
    public static final String SPACE = " ";
    public static final String BEARER = "Bearer".concat(SPACE);

    public static final String F = "F";
    public static final String T = "T";

    public static final String REGULARPHRASE_DATE = "^\\d{4}\\-(0[1-9]|1[012])\\-(0[1-9]|[12][0-9]|3[01])$";
    public static final String MESSAGE_NOT_FOUND = "No results were found.";
    public static final String MESSAGE_USER_NOT_FOUND = "The user does not exist.";
}
