package org.iesalixar.daw2.GarikAsatryan.valkyria.exceptions;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
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
     * Angular recibirá un 400 Bad Request con el mensaje traducido.
     */
    @ExceptionHandler(AppException.class)
    public ResponseEntity<Map<String, Object>> handleAppException(AppException ex, HttpServletRequest request) {
        String errorMessage = messageSource.getMessage(
                ex.getMessageKey(),
                ex.getArgs(),
                LocaleContextHolder.getLocale()
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(createErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                errorMessage,
                ex.getMessageKey(),
                request.getRequestURI()
        ));
    }

    /**
     * Captura errores de validación (@Valid en los DTOs).
     * Devuelve un mapa de campos y sus errores específicos.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidationExceptions(MethodArgumentNotValidException ex, HttpServletRequest request) {
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach((error) -> {
            if (error instanceof FieldError fieldError) {
                errors.put(fieldError.getField(), error.getDefaultMessage());
            } else {
                errors.put(error.getObjectName(), error.getDefaultMessage());
            }
        });

        Map<String, Object> response = createErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                "Validation Failed",
                "msg.validation.error",
                request.getRequestURI()
        );
        response.put("errors", errors);

        return ResponseEntity.badRequest().body(response);
    }

    /**
     * Captura cuando no se encuentra un recurso (404).
     */
    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<Map<String, Object>> handleNotFound(NoResourceFoundException ex, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(createErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                "Resource not found",
                "msg.error.not-found",
                request.getRequestURI()
        ));
    }

    /**
     * Captura cualquier otra excepción no controlada (500).
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleGeneralException(Exception ex, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(createErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "An unexpected error occurred",
                "msg.error.internal-server",
                request.getRequestURI()
        ));
    }

    /**
     * Método auxiliar para estandarizar la respuesta de error.
     */
    private Map<String, Object> createErrorResponse(int status, String message, String key, String path) {
        Map<String, Object> response = new HashMap<>();
        response.put("timestamp", LocalDateTime.now());
        response.put("status", status);
        response.put("message", message);
        response.put("key", key);
        response.put("path", path);
        return response;
    }
}