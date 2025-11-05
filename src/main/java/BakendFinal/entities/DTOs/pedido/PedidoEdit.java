package BakendFinal.entities.DTOs.pedido;

import java.util.List;

import BakendFinal.entities.models.DetallePedido;

public record  PedidoEdit(
    List<DetallePedido> detalles
){
    
}
