package org.iesalixar.daw2.GarikAsatryan.valkyria.services;

import net.coobird.thumbnailator.Thumbnails;
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

    private static final String FULL_SUFFIX = "_full.webp";
    private static final String THUMB_SUFFIX = "_thumb.webp";

    public String saveFile(MultipartFile file, String subFolder) throws IOException {
        if (file == null || file.isEmpty()) {
            return null;
        }

        Path uploadPath = Paths.get(uploadDirectory, subFolder);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        String baseName = UUID.randomUUID().toString();

        // SOLUCIÓN: Convertir a array de bytes para poder leerlo varias veces
        byte[] bytes = file.getBytes();

        try {
            // 1. Versión Full Size
            Thumbnails.of(new ByteArrayInputStream(bytes))
                    .size(1200, 1200)
                    .outputFormat("webp")
                    .outputQuality(0.80)
                    .toFile(uploadPath.resolve(baseName + FULL_SUFFIX).toFile());

            // 2. Versión Thumbnail
            Thumbnails.of(new ByteArrayInputStream(bytes))
                    .size(600, 600)
                    .outputFormat("webp")
                    .outputQuality(0.80)
                    .toFile(uploadPath.resolve(baseName + THUMB_SUFFIX).toFile());

            logger.info("Archivos guardados correctamente: {}{}", baseName, FULL_SUFFIX);
        } catch (Exception e) {
            logger.error("Error al procesar la imagen con Thumbnailator: {}", e.getMessage());
            throw new IOException("No se pudo procesar la imagen", e);
        }

        return baseName;
    }

    public void deleteFile(String baseName, String subFolder) {
        if (baseName == null || baseName.isEmpty()) return;

        try {
            Path folderPath = Paths.get(uploadDirectory, subFolder);
            boolean fullDeleted = Files.deleteIfExists(folderPath.resolve(baseName + FULL_SUFFIX));
            boolean thumbDeleted = Files.deleteIfExists(folderPath.resolve(baseName + THUMB_SUFFIX));

            if (fullDeleted || thumbDeleted) {
                logger.info("Archivos físicos eliminados para el baseName: {}", baseName);
            }
        } catch (IOException e) {
            logger.error("Error al eliminar los archivos físicos para: {}. Motivo: {}", baseName, e.getMessage());
        }
    }
}