package com.ardmore.quarters.gentlemens.exception;

public class InvalidTokenException extends RuntimeException {

    private static final long serialVersionUID = 458796123846L;

    public InvalidTokenException() {
        super();
    }

    public InvalidTokenException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public InvalidTokenException(final String message) {
        super(message);
    }

    public InvalidTokenException(final Throwable cause) {
        super(cause);
    }

}
