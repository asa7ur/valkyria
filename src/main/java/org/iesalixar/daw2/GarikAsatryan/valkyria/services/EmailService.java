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

@Service
@RequiredArgsConstructor
public class EmailService {

    private static final Logger logger = LoggerFactory.getLogger(EmailService.class);
    private final JavaMailSender mailSender;
    private final MessageSource messageSource;

    @Value("${app.url}")
    private String appUrl;

    /**
     * Envía el correo de activación al usuario tras el registro.
     */
    public void sendRegistrationConfirmationEmail(String to, String firstName, String token) {
        String confirmationUrl = appUrl + "/confirm-registration?token=" + token;

        String subject = getMessage("msg.register.email.subject", null);
        String bodyTemplate = getMessage("msg.register.email.body", null);
        String body = MessageFormat.format(bodyTemplate, firstName, confirmationUrl);

        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(to);
        email.setSubject(subject);
        email.setText(body);

        mailSender.send(email);
        logger.info("Correo de activación enviado a: {}", to);
    }

    /**
     * Envía el correo de confirmación de compra con el PDF adjunto.
     */
    public void sendOrderConfirmationEmail(Order order, byte[] pdfBytes) throws Exception {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

        String to = order.getUser().getEmail();
        String firstName = order.getUser().getFirstName();

        // Preparar asunto y cuerpo
        String subject = getMessage("msg.order.email.subject", new Object[]{order.getId()});
        String bodyTemplate = getMessage("msg.order.email.body", null);
        String body = MessageFormat.format(bodyTemplate, firstName, order.getId());

        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(body, true); // Soporta HTML

        // Adjuntar el PDF generado en memoria
        String fileName = "Valkyria_Ticket_Order_" + order.getId() + ".pdf";
        helper.addAttachment(fileName, new ByteArrayResource(pdfBytes));

        mailSender.send(message);
        logger.info("Confirmación de pedido #{} enviada a: {}", order.getId(), to);
    }

    /**
     * Helper para obtener mensajes localizados de forma más limpia.
     */
    private String getMessage(String key, Object[] args) {
        return messageSource.getMessage(key, args, LocaleContextHolder.getLocale());
    }
}