package org.iesalixar.daw2.GarikAsatryan.valkyria.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.file.Path;
import java.nio.file.Paths;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Value("${upload.directory:uploads}")
    private String uploadDirectory;

    /**
     * Configura el manejador de recursos para servir archivos desde una carpeta externa.
     * Mapea cualquier petición que empiece por /uploads/** a la carpeta física en el disco.
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        Path uploadPath = Paths.get(uploadDirectory).toAbsolutePath().normalize();
        String resourceLocation = "file:" + uploadPath + "/";

        registry.addResourceHandler("/uploads/**")
                .addResourceLocations(resourceLocation)
                .setCachePeriod(3600);
    }
}