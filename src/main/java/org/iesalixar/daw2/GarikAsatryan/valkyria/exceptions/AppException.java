package org.iesalixar.daw2.GarikAsatryan.valkyria.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class AppException extends RuntimeException {
    private final String messageKey;
    private final Object[] args;
    private final HttpStatus status;

    // Constructor por defecto
    public AppException(String messageKey, Object... args) {
        this(messageKey, HttpStatus.BAD_REQUEST, args);
    }

    // Constructor que permite especificar el estado HTTP
    public AppException(String messageKey, HttpStatus status, Object... args) {
        super(messageKey);
        this.messageKey = messageKey;
        this.status = status;
        this.args = args;
    }
}