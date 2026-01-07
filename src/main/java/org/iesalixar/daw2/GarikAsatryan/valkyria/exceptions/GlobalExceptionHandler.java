package org.iesalixar.daw2.GarikAsatryan.valkyria.exceptions;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Map;

@ControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    private final MessageSource messageSource;

    /**
     * Captura las excepciones de tipo AppException.
     * Si la petición es de la API, devuelve JSON.
     * Si es de la administración, redirige con un mensaje flash.
     */
    @ExceptionHandler(AppException.class)
    public Object handleAppException(AppException ex, HttpServletRequest request, RedirectAttributes redirectAttributes) {

        // 1. Traducir el mensaje
        String errorMessage = messageSource.getMessage(
                ex.getMessageKey(),
                ex.getArgs(),
                LocaleContextHolder.getLocale()
        );

        // 2. ¿Es una petición de la API (Angular)?
        if (request.getRequestURI().startsWith("/api/")) {
            return ResponseEntity.badRequest().body(Map.of(
                    "message", errorMessage,
                    "key", ex.getMessageKey()
            ));
        }

        // 3. Si es para el panel de Administración (Thymeleaf)
        redirectAttributes.addFlashAttribute("errorMessage", errorMessage);
        String referer = request.getHeader("Referer");

        // Si no hay referer (petición directa), vamos al dashboard de admin
        return "redirect:" + (referer != null ? referer : "/admin/dashboard");
    }

    /**
     * Captura excepciones generales para evitar que la API devuelva HTML en caso de error 500.
     */
    @ExceptionHandler(Exception.class)
    public Object handleGeneralException(Exception ex, HttpServletRequest request) {
        if (request.getRequestURI().startsWith("/api/")) {
            return ResponseEntity.internalServerError().body(Map.of(
                    "message", "Ocurrió un error inesperado en el servidor",
                    "details", ex.getMessage()
            ));
        }
        // Para admin, dejamos que Spring maneje el error 500 estándar (o tu página de error/500.html)
        throw new RuntimeException(ex);
    }
}