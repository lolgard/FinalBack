package BakendFinal.entities.DTOs.detallePedido;

public record DetallePedidoDTO(
    Long id,
    Long productoId,
    int cantidad,
    double subtotal
) {
    
}
