package org.iesalixar.daw2.GarikAsatryan.valkyrfest.components;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Component("urlBuilder")
public class UrlBuilderComponent {

    public String buildUrl(String... params) {
        ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attrs == null) return "";

        HttpServletRequest request = attrs.getRequest();
        // Usamos la clase base UriComponentsBuilder para mayor flexibilidad
        UriComponentsBuilder builder;

        // Si estamos en un POST (posible error de validación), usamos el Referer para volver al GET original
        if ("POST".equalsIgnoreCase(request.getMethod())) {
            String referer = request.getHeader("Referer");
            if (referer != null && !referer.isEmpty()) {
                builder = UriComponentsBuilder.fromUriString(referer);
            } else {
                builder = ServletUriComponentsBuilder.fromRequest(request);
            }
        } else {
            // En peticiones GET normales, usamos la URL actual de la petición
            builder = ServletUriComponentsBuilder.fromRequest(request);
        }

        // Aplicamos los parámetros de idioma (lang)
        for (int i = 0; i < params.length; i += 2) {
            if (i + 1 < params.length) {
                builder.replaceQueryParam(params[i], params[i + 1]);
            }
        }

        return builder.build().encode().toUriString();
    }
}