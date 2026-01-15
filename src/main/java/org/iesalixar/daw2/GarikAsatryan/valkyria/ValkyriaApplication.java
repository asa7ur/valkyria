package org.iesalixar.daw2.GarikAsatryan.valkyria;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.data.web.config.EnableSpringDataWebSupport.PageSerializationMode;

@SpringBootApplication
@EnableSpringDataWebSupport(pageSerializationMode = PageSerializationMode.VIA_DTO)
public class ValkyriaApplication {

    public static void main(String[] args) {
        SpringApplication.run(ValkyriaApplication.class, args);
    }
}