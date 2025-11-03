package BakendFinal.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import BakendFinal.services.BaseService;

@CrossOrigin(
    origins = {
        "${HOST_FRONTEND_1}",
        "${HOST_FRONTEND_2}"
    },
    allowCredentials = "true"
)
/*
 * D es el DTO
 * DC es el DTO de creación
 * DE es el DTO de edición
 * ID es el tipo de dato del ID (Long, String, etc)
 * S es el servicio correspondiente
 */
public abstract class BaseController<D, DC, DE, ID, S extends BaseService<D, DC, DE, ID>> {
    @Autowired
    protected S baseService;

    @GetMapping
    public List<D> listarTodos() {
        return baseService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerPorId(@PathVariable("id") ID id) {
        try {
            D entidad = baseService.obtenerPorId(id);
            return entidad != null ? ResponseEntity.ok(entidad) : ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> crear(@RequestBody DC dto) {
        try {
            return ResponseEntity.ok(baseService.crear(dto));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable("id") ID id, @RequestBody DE entidad) {
        try {
            return ResponseEntity.ok(baseService.actualizar(id, entidad));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable("id") ID id) {
        try {
            baseService.eliminar(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
