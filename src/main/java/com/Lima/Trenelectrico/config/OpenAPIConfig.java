package com.Lima.Trenelectrico.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class OpenAPIConfig {

    @Value("${trenelectrico.openapi.dev-url}")
    private String devUrl;

    @Value("${trenelectrico.openapi.prod-url}")
    private String prodUrl;

    @Bean
    public OpenAPI myOpenAPI() {
        // Configuración del servidor de desarrollo
        Server devServer = new Server();
        devServer.setUrl(devUrl);
        devServer.setDescription("Servidor de desarrollo del Tren Eléctrico");

        // Configuración del servidor de producción
        Server prodServer = new Server();
        prodServer.setUrl(prodUrl);
        prodServer.setDescription("Servidor de producción del Tren Eléctrico");

        // Información de contacto
        Contact contact = new Contact();
        contact.setEmail("soporte@trenelectrico.com");
        contact.setName("Soporte Tren Eléctrico");
        contact.setUrl("https://trenelectrico.com/soporte");

        // Licencia
        License mitLicense = new License().name("MIT License").url("https://choosealicense.com/licenses/mit/");

        // Información de la API
        Info info = new Info()
                .title("API del Tren Eléctrico")
                .version("1.0")
                .contact(contact)
                .description("Esta API proporciona servicios para gestionar el sistema de operaciones del Tren Eléctrico, incluyendo asistencia a PMR y monitoreo en tiempo real.")
                .termsOfService("https://trenelectrico.com/terminos")
                .license(mitLicense);

        // Devuelve la configuración de OpenAPI
        return new OpenAPI().info(info).servers(List.of(devServer, prodServer));
    }
}
