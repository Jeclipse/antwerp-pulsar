package com.antwerptechnologies.pulsar.data.to.pulsar.service.exception;

public class DataToPulsarServiceException extends RuntimeException {

    public DataToPulsarServiceException() {
        super();
    }

    public DataToPulsarServiceException(String message) {
        super(message);
    }

    public DataToPulsarServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
