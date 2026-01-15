package org.iesalixar.daw2.GarikAsatryan.valkyria.services;

import net.coobird.thumbnailator.Thumbnails;
import org.iesalixar.daw2.GarikAsatryan.valkyria.exceptions.AppException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileService {

    private static final Logger logger = LoggerFactory.getLogger(FileService.class);

    @Value("${upload.directory:uploads}")
    private String uploadDirectory;

    // Constantes para estandarizar el procesamiento de imágenes
    private static final String FULL_SUFFIX = "_full.webp";
    private static final String THUMB_SUFFIX = "_thumb.webp";
    private static final int FULL_WIDTH = 1200;
    private static final int FULL_HEIGHT = 1200;
    private static final int THUMB_WIDTH = 600;
    private static final int THUMB_HEIGHT = 600;
    private static final float OUTPUT_QUALITY = 0.80f;

    /**
     * Guarda una imagen generando dos versiones (Full y Thumb) en formato WebP.
     *
     * @return El nombre base (UUID) compartido por ambos archivos.
     */
    public String saveFile(MultipartFile file, String subFolder) {
        if (file == null || file.isEmpty()) {
            return null;
        }

        // Validar que sea una imagen (Opcional pero recomendado)
        if (file.getContentType() != null && !file.getContentType().startsWith("image/")) {
            throw new AppException("msg.file.not-an-image");
        }

        try {
            Path uploadPath = Paths.get(uploadDirectory, subFolder);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            String baseName = UUID.randomUUID().toString();
            byte[] bytes = file.getBytes();

            // 1. Generar Versión Full Size
            processImage(bytes, FULL_WIDTH, FULL_HEIGHT, uploadPath.resolve(baseName + FULL_SUFFIX));

            // 2. Generar Versión Thumbnail
            processImage(bytes, THUMB_WIDTH, THUMB_HEIGHT, uploadPath.resolve(baseName + THUMB_SUFFIX));

            logger.info("Imágenes WebP generadas con éxito: {}", baseName);
            return baseName;

        } catch (IOException e) {
            logger.error("Error crítico de E/S al guardar imagen: {}", e.getMessage());
            throw new AppException("msg.file.save-error");
        }
    }

    /**
     * Método privado auxiliar para evitar repetición de lógica con Thumbnailator.
     */
    private void processImage(byte[] source, int width, int height, Path destination) throws IOException {
        Thumbnails.of(new ByteArrayInputStream(source))
                .size(width, height)
                .outputFormat("webp")
                .outputQuality(OUTPUT_QUALITY)
                .toFile(destination.toFile());
    }

    /**
     * Elimina las dos versiones de la imagen del sistema de archivos.
     */
    public void deleteFile(String baseName, String subFolder) {
        if (baseName == null || baseName.isEmpty()) return;

        Path folderPath = Paths.get(uploadDirectory, subFolder);

        try {
            boolean fullDeleted = Files.deleteIfExists(folderPath.resolve(baseName + FULL_SUFFIX));
            boolean thumbDeleted = Files.deleteIfExists(folderPath.resolve(baseName + THUMB_SUFFIX));

            if (fullDeleted || thumbDeleted) {
                logger.info("Archivos físicos eliminados: {}{}", baseName, " (full/thumb)");
            }
        } catch (IOException e) {
            // Aquí no lanzamos AppException porque si el archivo no existe o no se puede borrar,
            // no queremos romper la transacción de la base de datos (idempotencia).
            logger.error("No se pudieron eliminar físicamente los archivos de: {}. Motivo: {}", baseName, e.getMessage());
        }
    }
}