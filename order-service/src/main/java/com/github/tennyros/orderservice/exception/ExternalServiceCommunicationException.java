package com.github.tennyros.orderservice.exception;

public class ExternalServiceCommunicationException extends RuntimeException {

    public ExternalServiceCommunicationException(String message) {
        super(message);
    }

    public ExternalServiceCommunicationException(String message, Throwable cause) {
        super(message, cause);
    }

}
