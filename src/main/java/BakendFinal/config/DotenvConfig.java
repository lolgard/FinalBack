package BakendFinal.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;

import io.github.cdimascio.dotenv.Dotenv;

public class DotenvConfig implements EnvironmentPostProcessor {

    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        try {
            // Buscar .env en múltiples ubicaciones
            Dotenv dotenv = null;
            String[] possiblePaths = {
                System.getProperty("user.dir"), // directorio actual
                System.getProperty("user.dir") + "/FinalBack", // si está ejecutando desde el padre
                "." // directorio actual como fallback
            };
            
            for (String path : possiblePaths) {
                try {
                    dotenv = Dotenv.configure()
                            .directory(path)
                            .ignoreIfMalformed()
                            .ignoreIfMissing()
                            .load();
                    System.out.println("📁 .env encontrado en: " + path);
                    break;
                } catch (Exception e) {
                    // Continuar con la siguiente ruta
                    System.out.println("Error:" + e.getMessage() + " al buscar .env en: " + path);
                    continue;
                }

            }
            
            if (dotenv == null) {
                dotenv = Dotenv.configure()
                        .ignoreIfMalformed()
                        .ignoreIfMissing()
                        .load(); // fallback al comportamiento por defecto
            }

            Map<String, Object> props = new HashMap<>();
            // la API de dotenv-java permite iterar entries; adaptamos de forma robusta
            try {
                dotenv.entries().forEach(entry -> props.put(entry.getKey().trim(), entry.getValue().trim()));
            } catch (Exception e) {
                // en caso de que entries() devuelva otro tipo, intentamos obtener variables explícitas conocidas
                String[] known = new String[]{"DB_HOST", "DB_PORT", "DB_NAME", "DB_USERNAME", "DB_PASSWORD", "SERVER_PORT", "HOST_FRONTEND_1", "HOST_FRONTEND_2"};
                for (String k : known) {
                    String v = dotenv.get(k);
                    if (v != null) props.put(k, v.trim());
                }
            }

            environment.getPropertySources().addFirst(new MapPropertySource("dotenv", props));
            System.out.println("✅ .env cargado temprano por DotenvConfig (property source 'dotenv' añadida)");
        } catch (Exception ex) {
            // No fallamos el arranque aquí: si dotenv no está presente, la app puede usar defaults o variables de entorno
            System.out.println("⚠️ No se pudo cargar .env con dotenv: " + ex.getMessage());
        }
    }
}
