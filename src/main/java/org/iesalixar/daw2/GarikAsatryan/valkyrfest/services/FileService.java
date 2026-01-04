package org.iesalixar.daw2.GarikAsatryan.valkyrfest.services;

import net.coobird.thumbnailator.Thumbnails;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileService {

    @Value("${upload.directory:uploads}")
    private String uploadDirectory;

    private static final String FULL_SUFFIX = "_full.webp";
    private static final String THUMB_SUFFIX = "_thumb.webp";

    /**
     * Saves two versions of an image: one full-size (max 1200px) and one thumbnail (max 300px).
     * Both are converted to WebP format for maximum compression.
     */
    public String saveFile(MultipartFile file, String subFolder) throws IOException {
        if (file == null || file.isEmpty()) {
            return null;
        }

        Path uploadPath = Paths.get(uploadDirectory, subFolder);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        // Generate a base UUID name without extension
        String baseName = UUID.randomUUID().toString();

        // 1. Save Full Size Version (Big)
        Thumbnails.of(file.getInputStream())
                .size(1200, 1200)
                .outputFormat("webp")
                .outputQuality(0.80)
                .toFile(uploadPath.resolve(baseName + FULL_SUFFIX).toFile());

        // 2. Save Thumbnail Version (Small)
        Thumbnails.of(file.getInputStream())
                .size(600, 600)
                .outputFormat("webp")
                .outputQuality(0.80)
                .toFile(uploadPath.resolve(baseName + THUMB_SUFFIX).toFile());

        // Return the baseName so the database only stores one ID
        return baseName;
    }

    /**
     * Deletes both the full-size and thumbnail versions from the disk.
     */
    public void deleteFile(String baseName, String subFolder) {
        if (baseName == null || baseName.isEmpty()) return;

        try {
            Path folderPath = Paths.get(uploadDirectory, subFolder);
            Files.deleteIfExists(folderPath.resolve(baseName + FULL_SUFFIX));
            Files.deleteIfExists(folderPath.resolve(baseName + THUMB_SUFFIX));
        } catch (IOException e) {
            System.err.println("Error deleting resized images for: " + baseName);
        }
    }
}