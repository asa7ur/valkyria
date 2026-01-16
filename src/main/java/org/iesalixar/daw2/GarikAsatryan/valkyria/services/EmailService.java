package org.iesalixar.daw2.GarikAsatryan.valkyria.services;

import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.iesalixar.daw2.GarikAsatryan.valkyria.entities.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;

/**
 * Servicio de gestión de correos electrónicos.
 * Encargado de enviar notificaciones por email en diferentes flujos del sistema:
 * - Confirmación de registro de usuarios
 * - Confirmación de compras/pedidos con tickets adjuntos
 * <p>
 * Utiliza plantillas i18n para soportar múltiples idiomas y JavaMailSender para el envío.
 */
@Service
@RequiredArgsConstructor
public class EmailService {

    private static final Logger logger = LoggerFactory.getLogger(EmailService.class);

    // Inyección de dependencias mediante constructor (Lombok @RequiredArgsConstructor)
    private final JavaMailSender mailSender;
    private final MessageSource messageSource;

    // URL base de la aplicación configurada en application.properties/yml
    @Value("${app.url}")
    private String appUrl;

    /**
     * Envía un correo electrónico de confirmación de registro al usuario.
     * El correo incluye un enlace de activación con un token temporal.
     * <p>
     * Flujo:
     * 1. Construye la URL de confirmación con el token
     * 2. Obtiene las plantillas de mensaje localizadas
     * 3. Formatea el cuerpo del mensaje con los datos del usuario
     * 4. Envía el correo mediante SMTP
     *
     * @param to        Email del destinatario (usuario recién registrado)
     * @param firstName Nombre del usuario para personalizar el mensaje
     * @param token     Token de activación único generado para este registro
     */
    public void sendRegistrationConfirmationEmail(String to, String firstName, String token) {
        logger.info("Iniciando envío de correo de activación a: {}", to);
        logger.debug("Token de activación generado: {}", token);

        // Paso 1: Construir la URL completa de confirmación
        String confirmationUrl = appUrl + "/confirm-registration?token=" + token;
        logger.debug("URL de confirmación generada: {}", confirmationUrl);

        // Paso 2: Obtener textos localizados según el idioma del contexto actual
        String subject = getMessage("msg.register.email.subject", null);
        String bodyTemplate = getMessage("msg.register.email.body", null);

        logger.debug("Locale actual: {}", LocaleContextHolder.getLocale());

        // Paso 3: Formatear el cuerpo del mensaje con los parámetros
        // Parámetros: {0} = nombre del usuario, {1} = URL de confirmación
        String body = MessageFormat.format(bodyTemplate, firstName, confirmationUrl);

        // Paso 4: Construir el mensaje de correo simple (texto plano)
        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(to);
        email.setSubject(subject);
        email.setText(body);

        logger.debug("Mensaje preparado. Asunto: '{}'", subject);

        // Paso 5: Enviar el correo mediante JavaMailSender
        try {
            mailSender.send(email);
            logger.info("Correo de activación enviado exitosamente a: {}", to);
        } catch (Exception e) {
            logger.error("Error al enviar correo de activación a {}: {}", to, e.getMessage(), e);
            throw e; // Re-lanzar para que el controlador pueda manejar el error
        }
    }

    /**
     * Envía un correo de confirmación de compra con el ticket/entrada en formato PDF adjunto.
     * Soporta tanto usuarios registrados como invitados (guest checkout).
     * <p>
     * Flujo:
     * 1. Determina el destinatario (usuario registrado o email de invitado)
     * 2. Crea un mensaje MIME para soportar adjuntos
     * 3. Obtiene plantillas localizadas y formatea el contenido
     * 4. Adjunta el PDF generado en memoria
     * 5. Envía el correo
     *
     * @param order    Entidad del pedido que contiene información del comprador y productos
     * @param pdfBytes Array de bytes del PDF generado (ticket/entrada)
     * @throws Exception si hay errores en la construcción o envío del mensaje
     */
    public void sendOrderConfirmationEmail(Order order, byte[] pdfBytes) throws Exception {
        logger.info("Iniciando envío de confirmación de pedido #{}", order.getId());

        // Paso 1: Crear mensaje MIME (necesario para adjuntos)
        MimeMessage message = mailSender.createMimeMessage();

        // Helper para facilitar la construcción del mensaje
        // true = soporta multipart (adjuntos), UTF-8 = encoding
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

        // Paso 2: Determinar destinatario según tipo de compra (registrado vs invitado)
        String to;
        String firstName;

        if (order.getUser() != null) {
            // Usuario registrado
            to = order.getUser().getEmail();
            firstName = order.getUser().getFirstName();
            logger.debug("Pedido de usuario registrado: {} ({})", firstName, to);
        } else {
            // Compra como invitado
            to = order.getGuestEmail();
            firstName = "Invitado"; // Nombre genérico para invitados
            logger.debug("Pedido de invitado: {}", to);
        }

        logger.debug("Destinatario determinado: {} ({})", firstName, to);

        // Paso 3: Obtener y formatear las plantillas de mensaje localizadas
        String subject = getMessage("msg.order.email.subject", new Object[]{order.getId()});
        String bodyTemplate = getMessage("msg.order.email.body", null);

        // Formatear el cuerpo con: {0} = nombre, {1} = ID del pedido
        String body = MessageFormat.format(bodyTemplate, firstName, order.getId());

        logger.debug("Asunto del email: '{}'", subject);

        // Paso 4: Configurar el mensaje
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(body, true); // true = el contenido puede ser HTML

        // Paso 5: Adjuntar el PDF generado en memoria (no se guarda en disco)
        String fileName = "Valkyria_Ticket_Order_" + order.getId() + ".pdf";
        helper.addAttachment(fileName, new ByteArrayResource(pdfBytes));

        logger.debug("PDF adjunto: {} ({} bytes)", fileName, pdfBytes.length);

        // Paso 6: Enviar el correo
        try {
            mailSender.send(message);
            logger.info("Confirmación de pedido #{} enviada exitosamente a: {}",
                    order.getId(), to);
        } catch (Exception e) {
            logger.error("Error al enviar confirmación de pedido #{} a {}: {}",
                    order.getId(), to, e.getMessage(), e);
            throw e; // Re-lanzar para que el llamador pueda manejar el error
        }
    }

    /**
     * Método helper privado para obtener mensajes localizados del MessageSource.
     * Encapsula la lógica de internacionalización en un solo lugar.
     *
     * @param key  Clave del mensaje en los archivos de propiedades (messages.properties)
     * @param args Argumentos para interpolar en el mensaje (puede ser null)
     * @return Mensaje localizado según el locale actual del contexto
     */
    private String getMessage(String key, Object[] args) {
        return messageSource.getMessage(key, args, LocaleContextHolder.getLocale());
    }
}