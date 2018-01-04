package com.sizonenko.xmlapp.exception;

public class EnumInvalidTypeException extends Exception {

    public EnumInvalidTypeException() {
        super();
    }

    public EnumInvalidTypeException(String message) {
        super(message);
    }

    public EnumInvalidTypeException(String message, Throwable cause) {
        super(message, cause);
    }

    public EnumInvalidTypeException(Throwable cause) {
        super(cause);
    }
}
