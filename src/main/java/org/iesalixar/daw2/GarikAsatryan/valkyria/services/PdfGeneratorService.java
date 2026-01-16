package org.iesalixar.daw2.GarikAsatryan.valkyria.services;

import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfWriter;
import lombok.RequiredArgsConstructor;
import org.iesalixar.daw2.GarikAsatryan.valkyria.entities.Camping;
import org.iesalixar.daw2.GarikAsatryan.valkyria.entities.Order;
import org.iesalixar.daw2.GarikAsatryan.valkyria.entities.Ticket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;

/**
 * Servicio de generación de documentos PDF para pedidos.
 * Crea credenciales/tickets en formato PDF con códigos QR para validación.
 * <p>
 * Funcionalidades:
 * - Generación de PDF con información del pedido
 * - Inclusión de códigos QR únicos por cada entrada/camping
 * - Soporte para usuarios registrados e invitados
 * - Formato estructurado con separación visual por item
 * <p>
 * El PDF generado se envía por email al usuario y puede descargarse desde el panel de pedidos.
 * Utiliza OpenPDF (fork de iText) para la generación de documentos.
 */
@Service
@RequiredArgsConstructor
public class PdfGeneratorService {

    private static final Logger logger = LoggerFactory.getLogger(PdfGeneratorService.class);

    // Inyección del servicio de generación de códigos QR
    private final QrCodeService qrCodeService;

    /**
     * Genera un documento PDF completo con todas las credenciales de un pedido.
     * El PDF incluye una "tarjeta" por cada ticket y camping reservado, cada una con su QR único.
     * <p>
     * Estructura del PDF:
     * 1. Título del documento (VALKYRIA - CREDENCIALES)
     * 2. Información del pedido (ID, comprador)
     * 3. Tarjetas individuales para cada ticket
     * 4. Tarjetas individuales para cada camping
     * <p>
     * Cada tarjeta contiene:
     * - Tipo de entrada/camping
     * - Nombre del asistente
     * - Código QR para validación en el evento
     *
     * @param order Pedido para el cual generar el PDF
     * @return Array de bytes del PDF generado (se mantiene en memoria, no se guarda en disco)
     * @throws Exception si hay error en la generación del PDF o los códigos QR
     */
    public byte[] generateOrderPdf(Order order) throws Exception {
        logger.info("Iniciando generación de PDF para pedido #{}", order.getId());
        logger.debug("Pedido contiene {} tickets y {} campings",
                order.getTickets().size(),
                order.getCampings().size());

        // Paso 1: Crear el stream de salida en memoria (no guardamos en disco)
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        // Paso 2: Crear documento PDF con tamaño A4 estándar
        Document document = new Document(PageSize.A4);

        // Paso 3: Asociar el writer al documento y al stream de salida
        PdfWriter.getInstance(document, out);
        logger.debug("Documento PDF inicializado (A4)");

        // Paso 4: Abrir el documento para poder escribir contenido
        document.open();
        logger.debug("Documento PDF abierto, comenzando a escribir contenido");

        // ==================== SECCIÓN: TÍTULO PRINCIPAL ====================

        Font fontTitle = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 24);
        Paragraph title = new Paragraph("VALKYRIA - CREDENCIALES", fontTitle);
        title.setAlignment(Element.ALIGN_CENTER);
        document.add(title);
        logger.debug("Título principal añadido");

        // Espacio en blanco para separación visual
        document.add(new Paragraph(" "));

        // ==================== SECCIÓN: INFORMACIÓN DEL PEDIDO ====================

        // Añadir ID del pedido
        document.add(new Paragraph("Pedido ID: " + order.getId()));
        logger.debug("ID de pedido añadido: #{}", order.getId());

        // Determinar el nombre del comprador según sea usuario registrado o invitado
        String explorerName;
        if (order.getUser() != null) {
            // Usuario registrado: mostrar nombre completo
            explorerName = order.getUser().getFirstName() + " " + order.getUser().getLastName();
            logger.debug("Comprador (registrado): {}", explorerName);
        } else {
            // Invitado: mostrar email
            explorerName = "Invitado (" + order.getGuestEmail() + ")";
            logger.debug("Comprador (invitado): {}", order.getGuestEmail());
        }

        document.add(new Paragraph("Explorador: " + explorerName));

        // Espacio antes de las tarjetas
        document.add(new Paragraph(" "));

        // ==================== SECCIÓN: ENTRADAS (TICKETS) ====================

        if (!order.getTickets().isEmpty()) {
            logger.info("Generando {} tarjetas de entradas en el PDF", order.getTickets().size());

            int ticketIndex = 0;
            for (Ticket ticket : order.getTickets()) {
                ticketIndex++;

                logger.debug("Procesando tarjeta de entrada {}/{}: {} para {}",
                        ticketIndex,
                        order.getTickets().size(),
                        ticket.getTicketType().getName(),
                        ticket.getFirstName() + " " + ticket.getLastName());

                // Generar tarjeta con QR para este ticket
                addEntryToPdf(
                        document,
                        "ENTRADA: " + ticket.getTicketType().getName(),
                        ticket.getFirstName() + " " + ticket.getLastName(),
                        ticket.getQrCode()
                );
            }

            logger.info("✓ Todas las tarjetas de entradas generadas correctamente");
        } else {
            logger.debug("No hay entradas en este pedido");
        }

        // ==================== SECCIÓN: RESERVAS DE CAMPING ====================

        if (!order.getCampings().isEmpty()) {
            logger.info("Generando {} tarjetas de camping en el PDF", order.getCampings().size());

            int campingIndex = 0;
            for (Camping camping : order.getCampings()) {
                campingIndex++;

                logger.debug("Procesando tarjeta de camping {}/{}: {} para {}",
                        campingIndex,
                        order.getCampings().size(),
                        camping.getCampingType().getName(),
                        camping.getFirstName() + " " + camping.getLastName());

                // Generar tarjeta con QR para esta reserva
                addEntryToPdf(
                        document,
                        "CAMPING: " + camping.getCampingType().getName(),
                        camping.getFirstName() + " " + camping.getLastName(),
                        camping.getQrCode()
                );
            }

            logger.info("✓ Todas las tarjetas de camping generadas correctamente");
        } else {
            logger.debug("No hay reservas de camping en este pedido");
        }

        // ==================== FINALIZACIÓN ====================

        // Cerrar el documento (esto finaliza la escritura del PDF)
        document.close();
        logger.debug("Documento PDF cerrado");

        // Convertir el stream a array de bytes
        byte[] pdfBytes = out.toByteArray();

        logger.info("✓ PDF generado exitosamente para pedido #{}. Tamaño: {} bytes ({} KB)",
                order.getId(),
                pdfBytes.length,
                pdfBytes.length / 1024);

        return pdfBytes;
    }

    /**
     * Método helper privado para añadir una tarjeta individual al PDF.
     * Cada tarjeta representa un ticket o reserva de camping con su código QR.
     * <p>
     * Estructura de la tarjeta:
     * - Línea separadora visual
     * - Tipo de entrada/camping (en negrita, tamaño 14)
     * - Nombre del asistente
     * - Código QR (100x100 px)
     * <p>
     * El código QR se genera dinámicamente con el identificador único del item
     * y puede escanearse en el evento para validar la entrada/reserva.
     *
     * @param document Documento PDF al que añadir la tarjeta
     * @param type     Descripción del tipo (ej: "ENTRADA: VIP", "CAMPING: Confort")
     * @param name     Nombre completo del asistente
     * @param qrData   Datos a codificar en el QR (código único del ticket/camping)
     * @throws Exception si hay error generando el QR o añadiendo elementos al PDF
     */
    private void addEntryToPdf(Document document, String type, String name, String qrData) throws Exception {
        logger.trace("Añadiendo tarjeta al PDF: Tipo='{}', Asistente='{}', QR='{}'",
                type, name, qrData);

        // Línea separadora visual entre tarjetas
        document.add(new Paragraph("-----------------------------------------------------------------------"));

        // Tipo de entrada/camping en negrita y tamaño destacado
        Font boldFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14);
        document.add(new Paragraph(type, boldFont));

        // Nombre del asistente
        document.add(new Paragraph("Asistente: " + name));

        // ==================== GENERACIÓN Y AÑADIDO DEL CÓDIGO QR ====================

        logger.trace("Generando código QR para: {}", qrData);

        // Generar imagen QR como array de bytes (PNG)
        byte[] qrBytes = qrCodeService.generateQrCodeImage(qrData);
        logger.trace("QR generado: {} bytes", qrBytes.length);

        // Crear objeto Image de OpenPDF desde los bytes
        Image qrImage = Image.getInstance(qrBytes);

        // Escalar la imagen a tamaño apropiado (100x100 px)
        // Mantiene el aspect ratio, la imagen QR es cuadrada
        qrImage.scaleToFit(100, 100);

        // Añadir la imagen al documento
        document.add(qrImage);

        logger.trace("✓ Tarjeta añadida correctamente al documento");
    }
}