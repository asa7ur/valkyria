package org.iesalixar.daw2.GarikAsatryan.valkyria.exceptions;

import lombok.RequiredArgsConstructor;
import org.iesalixar.daw2.GarikAsatryan.valkyria.dtos.ResponseDTO;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    private final MessageSource messageSource;

    /**
     * Captura errores de lógica de negocio (AppException).
     * Utiliza el MessageSource para traducir el mensaje basado en la clave.
     */
    @ExceptionHandler(AppException.class)
    public ResponseEntity<ResponseDTO<Void>> handleAppException(AppException ex) {
        String errorMessage = messageSource.getMessage(
                ex.getMessageKey(),
                ex.getArgs(),
                LocaleContextHolder.getLocale()
        );

        return ResponseEntity
                .status(ex.getStatus())
                .body(ResponseDTO.error(ex.getStatus().value(), errorMessage));
    }

    /**
     * Captura errores de validación (@Valid en los DTOs).
     * Devuelve los errores específicos de cada campo en el atributo 'data' del DTO.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseDTO<Map<String, String>>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach((error) -> {
            if (error instanceof FieldError fieldError) {
                errors.put(fieldError.getField(), error.getDefaultMessage());
            } else {
                errors.put(error.getObjectName(), error.getDefaultMessage());
            }
        });

        ResponseDTO<Map<String, String>> response = new ResponseDTO<>(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                "Validation Failed",
                null,
                errors
        );

        return ResponseEntity.badRequest().body(response);
    }

    /**
     * Captura cuando no se encuentra un recurso (404).
     */
    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<ResponseDTO<Void>> handleNotFound(NoResourceFoundException ex) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ResponseDTO.error(HttpStatus.NOT_FOUND.value(), "Resource not found"));
    }

    /**
     * Captura cualquier otra excepción no controlada (500).
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseDTO<Void>> handleGeneralException(Exception ex) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ResponseDTO.error(HttpStatus.INTERNAL_SERVER_ERROR.value(), "An unexpected error occurred"));
    }
}