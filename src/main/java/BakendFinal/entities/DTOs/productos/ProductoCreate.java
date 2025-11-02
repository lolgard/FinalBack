package BakendFinal.entities.DTOs.productos;

public record ProductoCreate(
    String nombre,
    double precio,
    String marca,
    Long categoriaId,
    String imagen,
    int stock
) {
    
}
