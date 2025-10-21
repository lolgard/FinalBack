package BakendFinal.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;

import io.github.cdimascio.dotenv.Dotenv;
import jakarta.annotation.PostConstruct;

import java.util.HashMap;
import java.util.Map;

@Configuration // Le dice a Spring que esta clase contiene configuración de beans y debe procesarla
@Lazy // Retrasa la creación de esta clase hasta que se necesite (evita problemas de dependencias circulares)
@DependsOn("environment") // Garantiza que el bean "environment" esté disponible antes de crear esta clase
public class EnvConfig {
    
    @Autowired // Spring inyectará automáticamente la dependencia ConfigurableEnvironment
    private ConfigurableEnvironment environment; // Objeto que maneja todas las propiedades de configuración de Spring

    @PostConstruct // Este método se ejecuta DESPUÉS de que Spring haya creado la instancia y hecho las inyecciones
    public void loadEnvVariables() {
        try {
            // Configuración de dotenv para leer el archivo .env
            Dotenv dotenv = Dotenv.configure()
                    .directory("./") // Busca el archivo .env en el directorio raíz del proyecto
                    .ignoreIfMalformed() // Si el archivo .env tiene errores de formato, no falla la aplicación
                    .ignoreIfMissing() // Si no encuentra el archivo .env, no falla la aplicación
                    .load(); // Carga el archivo .env en memoria

            // Crear un mapa para convertir las variables del .env en propiedades de Spring
            Map<String, Object> dotenvProperties = new HashMap<>();
            
            // Iterar sobre cada entrada del archivo .env
            dotenv.entries().forEach(entry -> {
                // Agregar cada variable al mapa, eliminando espacios en blanco
                dotenvProperties.put(entry.getKey().trim(), entry.getValue().trim());
            });

            // Agregar las variables del .env como la PRIMERA fuente de propiedades de Spring
            // Esto significa que tendrán prioridad sobre application.properties
            environment.getPropertySources()
                    .addFirst(new MapPropertySource("dotenv", dotenvProperties));
            // Mensajes informativos para confirmar que todo funcionó
            System.out.println("✅ Archivo .env cargado exitosamente");

        } catch (Exception e) {
            // Si algo sale mal, mostrar el error pero no detener la aplicación
            System.err.println("⚠️ Error al cargar .env: " + e.getMessage());
        }
    }
}
