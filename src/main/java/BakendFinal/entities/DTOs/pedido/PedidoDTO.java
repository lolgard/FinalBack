package BakendFinal.entities.DTOs.pedido;

import java.time.LocalDate;
import java.util.List;

import BakendFinal.entities.DTOs.detallePedido.DetallePedidoDTO;
import BakendFinal.entities.DTOs.informacionDeEntrega.InformacionDeEntregaDTO;
import BakendFinal.entities.enums.Estado;

public record PedidoDTO(
    Long id,
    LocalDate fecha,
    Estado estado,
    List<DetallePedidoDTO> detalles,
    double total,
    Long usuarioId,
    InformacionDeEntregaDTO informacionDeEntrega
) {
}
