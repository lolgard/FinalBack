package BakendFinal.entities.DTOs.productos;

public record ProductoDTO(
    Long id,
    String nombre,
    double precio,
    String marca,
    String categoriaNombre
) {
    
}
