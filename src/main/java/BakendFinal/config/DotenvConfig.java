package BakendFinal.config;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;

import java.util.HashMap;
import java.util.Map;

public class DotenvConfig implements EnvironmentPostProcessor {

    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        try {
            Dotenv dotenv = Dotenv.configure()
                    .ignoreIfMalformed()
                    .ignoreIfMissing()
                    .load(); // busca en el working directory por defecto

            Map<String, Object> props = new HashMap<>();
            // la API de dotenv-java permite iterar entries; adaptamos de forma robusta
            try {
                dotenv.entries().forEach(entry -> props.put(entry.getKey().trim(), entry.getValue().trim()));
            } catch (Exception e) {
                // en caso de que entries() devuelva otro tipo, intentamos obtener variables explícitas conocidas
                String[] known = new String[]{"DB_HOST", "DB_PORT", "DB_NAME", "DB_USER", "DB_PASS"};
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
