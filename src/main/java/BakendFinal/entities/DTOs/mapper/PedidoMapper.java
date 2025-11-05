package BakendFinal.entities.DTOs.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import BakendFinal.entities.DTOs.detallePedido.DetallePedidoDTO;
import BakendFinal.entities.DTOs.pedido.PedidoCreate;
import BakendFinal.entities.DTOs.pedido.PedidoDTO;
import BakendFinal.entities.models.DetallePedido;
import BakendFinal.entities.models.Pedido;

public class PedidoMapper implements BaseMapper<Pedido, PedidoDTO, PedidoCreate> {

    @Autowired
    private DetallePedidoMapper detallePedidoMapper;

    @Override
    public Pedido toEntity(PedidoCreate d) {
        Pedido pedido = Pedido.builder()
                .fecha(d.fecha())
                .build();

        List<DetallePedido> detalles = d.detalles().stream()
        .map(detalleCreate->{
            DetallePedido detalle = detallePedidoMapper.toEntity(detalleCreate,pedido);
            detalle.setPedido(pedido);
            return detalle;
        }).collect(Collectors.toList());
        pedido.setDetallePedidos(detalles);
        double total = detalles.stream()
                .mapToDouble(DetallePedido::getSubtotal)
                .sum();
        pedido.setTotal(total);
        return pedido;
    }

    @Override
    public PedidoDTO toDto(Pedido e) {
        List<DetallePedido> detalles = e.getDetallePedidos();
        List<DetallePedidoDTO> detalleDTOs = detalles.stream()
                .map(detallePedidoMapper::toDto)
                .collect(Collectors.toList());

        return new PedidoDTO(
                e.getId(),
                e.getFecha(),
                e.getEstado(),
                detalleDTOs,
                e.getTotal()
        );
    }

}
