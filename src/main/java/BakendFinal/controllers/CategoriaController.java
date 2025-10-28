package BakendFinal.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import BakendFinal.entities.DTOs.categorias.CategoriaDTO;
import BakendFinal.entities.DTOs.categorias.CategoriaCreate;
import BakendFinal.entities.DTOs.categorias.CategoriaEdit;
import BakendFinal.services.categorias.CategoriaService;

@RestController
@RequestMapping("/categorias")
public class CategoriaController extends BaseController<CategoriaDTO, CategoriaCreate, CategoriaEdit, Long, CategoriaService> {
    // Hereda todos los endpoints CRUD de BaseController
}