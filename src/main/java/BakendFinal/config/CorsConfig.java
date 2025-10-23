package BakendFinal.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        
        // Permitir credenciales (cookies, headers de autorización, etc.)
        config.setAllowCredentials(true);
        
        // Permitir el origen de tu frontend
        config.setAllowedOrigins(Arrays.asList("http://localhost:5173"));
        
        // Si tienes otros puertos o dominios, agrégalos aquí:
        // config.setAllowedOrigins(Arrays.asList("http://localhost:5173", "http://localhost:3000"));
        
        // Permitir todos los headers
        config.addAllowedHeader("*");
        
        // Permitir todos los métodos HTTP (GET, POST, PUT, DELETE, etc.)
        config.addAllowedMethod("*");
        
        // Aplicar esta configuración a todos los endpoints
        source.registerCorsConfiguration("/**", config);
        
        return new CorsFilter(source);
    }
}
