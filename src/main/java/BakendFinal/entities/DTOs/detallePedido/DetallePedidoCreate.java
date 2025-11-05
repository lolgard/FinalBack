package BakendFinal.entities.DTOs.detallePedido;

public record DetallePedidoCreate(
    int cantidad,
    Long productoId,
    Long pedidoId
) {

}
