package com.mmall.concurrency.gayHub.redLimiter;

public class CreateException extends RuntimeException {

    public CreateException() {
    }

    public CreateException(String message) {
        super(message);
    }

    public CreateException(Throwable cause) {
        super(cause);
    }

    public CreateException(String message, Throwable cause) {
        super(message, cause);
    }

}
