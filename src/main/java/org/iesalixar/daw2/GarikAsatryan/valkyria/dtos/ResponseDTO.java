package org.iesalixar.daw2.GarikAsatryan.valkyria.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL) // Evita enviar campos null al cliente
public class ResponseDTO<T> {
    private LocalDateTime timestamp;
    private int status;
    private boolean success;
    private String message;
    private FilterDTO filter;
    private T data;

    public static <T> ResponseDTO<T> success(String message, T data) {
        return success(message, data, null);
    }

    public static <T> ResponseDTO<T> success(String message, T data, FilterDTO filter) {
        return new ResponseDTO<>(LocalDateTime.now(), 200, true, message, filter, data);
    }

    public static <T> ResponseDTO<T> error(int status, String message) {
        return error(status, message, null);
    }

    public static <T> ResponseDTO<T> error(int status, String message, T data) {
        return new ResponseDTO<>(LocalDateTime.now(), status, false, message, null, data);
    }
}