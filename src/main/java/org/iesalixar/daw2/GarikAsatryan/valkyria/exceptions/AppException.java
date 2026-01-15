package org.iesalixar.daw2.GarikAsatryan.valkyria.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class AppException extends RuntimeException {
    private final String messageKey;
    private final Object[] args;
    private final HttpStatus status;

    public AppException(String messageKey, Object... args) {
        super(messageKey);
        this.messageKey = messageKey;
        this.args = args;
        this.status = HttpStatus.BAD_REQUEST;
    }
}
