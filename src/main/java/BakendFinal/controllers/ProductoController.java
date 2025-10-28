package BakendFinal.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import BakendFinal.entities.DTOs.productos.ProductoDTO;
import BakendFinal.entities.DTOs.productos.ProductoCreate;
import BakendFinal.entities.DTOs.productos.ProductoEdit;
import BakendFinal.services.productos.ProductoService;

@RestController
@RequestMapping("/productos")
public class ProductoController extends BaseController<ProductoDTO, ProductoCreate, ProductoEdit, Long, ProductoService> {
    // Hereda todos los endpoints CRUD de BaseController
}