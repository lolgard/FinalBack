package BakendFinal.entities.DTOs.pedido;

import java.time.LocalDate;
import java.util.List;

import BakendFinal.entities.DTOs.detallePedido.DetallePedidoCreate;

public record PedidoCreate(
    LocalDate fecha,
    List<DetallePedidoCreate> detalles
) {
    
}
