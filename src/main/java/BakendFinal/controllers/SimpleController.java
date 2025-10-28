package BakendFinal.controllers;

import java.sql.Connection;
import java.time.Instant;
import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;

// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.PostMapping;


@RestController
@CrossOrigin(origins = "*")
 public class SimpleController {
//     //DataSource: bean que proporciona las conexiones de JDBC
//     //final porque la dependencia se inyecta una vez en el constructor
//     private final DataSource baseDeDatos;

    //Inyeccion por constructor
    //Spring busca un bean DataSource y lo pasa al constructor
    // public SimpleController(DataSource baseDeDatos) {
    //     this.baseDeDatos = baseDeDatos;
    // }

    @GetMapping("/")
    public String home() {
        return "Backend funcionando! Coso";
    }

    @GetMapping("/ping")
    public String ping() {
        return "pongo";
    }

    //Post Status para chequear si la base de datos está levantada
    // @PostMapping("/status")
    // public ResponseEntity<Map<String,Object>> status() {
    //     //dbLevantada indica si la base de datos responde
    //     boolean dbLevantada = false;

    //     //Try-catch-resources: cierra la conexion automaticamente
    //     //baseDeDatos.getConnection(): obtiene una conexion del pool
    //     try(Connection conexion = baseDeDatos.getConnection()){
    //         //Si la conexion no es null y no está cerrada, la DB está levantada
    //         dbLevantada = conexion != null && !conexion.isClosed();
    //     } catch (Exception e) {
    //         //Si ocurre una excepcion, la DB no está levantada
    //         dbLevantada = false;
    //     }

    //     // //Construccion de la respuesta en formato map que se serializa a JSON por defecto gracias a Spring Boot y RestController
        // Map<String,Object> body = Map.of(
        //     "estado", dbLevantada ? "UP" : "DOWN",
        //     "db", dbLevantada,
        //     "tiempo", Instant.now().toString()
        // );
        // // ResponseEntity.status(...).body(body):
        // // - Si dbLevantada == true -> devolvemos 200 OK (HttpStatus.OK)
        // // - Si dbLevantada == false -> devolvemos 503 Service Unavailable (HttpStatus.SERVICE_UNAVAILABLE)
        // //
        // // Por qué no usar siempre ResponseEntity.ok(body):
        // // - devolver siempre 200 oculta el estado real del servicio a nivel HTTP.
        // // - devolver 503 permite a frontend/monitores/proxies actuar según el código HTTP.
        // return ResponseEntity.status(dbLevantada? HttpStatus.OK :HttpStatus.SERVICE_UNAVAILABLE).body(body);
    
    
}