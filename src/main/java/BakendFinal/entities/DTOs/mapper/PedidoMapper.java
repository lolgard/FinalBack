package BakendFinal.entities.DTOs.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import BakendFinal.entities.DTOs.detallePedido.DetallePedidoDTO;
import BakendFinal.entities.DTOs.pedido.PedidoCreate;
import BakendFinal.entities.DTOs.pedido.PedidoDTO;
import BakendFinal.entities.enums.Estado;
import BakendFinal.entities.models.DetallePedido;
import BakendFinal.entities.models.InformacionDeEntrega;
import BakendFinal.entities.models.Pedido;
import BakendFinal.entities.models.Usuario;
import BakendFinal.repositories.UsuarioRepository;


@Component
public class PedidoMapper implements BaseMapper<Pedido, PedidoDTO, PedidoCreate> {

    @Autowired
    private DetallePedidoMapper detallePedidoMapper;

    @Autowired
    private InformacionDeEntregaMapper informacionDeEntregaMapper;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public Pedido toEntity(PedidoCreate d) {
        // Validar y obtener usuario
        if (d.usuarioId() == null) {
            throw new IllegalArgumentException("Usuario es requerido");
        }
       
        Usuario usuario = usuarioRepository.findById(d.usuarioId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + d.usuarioId()));
        
        // Crear el pedido primero
        Pedido pedido = Pedido.builder()
                .fecha(d.fecha())
                .total(0.0)
                .estado(Estado.PENDIENTE)
                .usuario(usuario)
                .detallePedidos(new ArrayList<>())
                .informacionDeEntrega(new ArrayList<>())
                .build();

        // Mapear y establecer detalles
        if (d.detalles() != null && !d.detalles().isEmpty()) {
            List<DetallePedido> detalles = d.detalles().stream()
                    .map(detallePedidoMapper::toEntity)
                    .collect(Collectors.toList());

            // Establecer la relación bidireccional: Pedido <-> Detalles
            detalles.forEach(detalle -> {                
                pedido.getDetallePedidos().add(detalle);
            });

            // Calcular el total
            double total = detalles.stream()
                    .mapToDouble(DetallePedido::getSubtotal)
                    .sum();
            pedido.setTotal(total);
        }

        // Mapear información de entrega
        if (d.informacionDeEntrega() != null) {
            InformacionDeEntrega infoEntrega = informacionDeEntregaMapper.toEntity(d.informacionDeEntrega());
            infoEntrega.setPedido(pedido);
            pedido.getInformacionDeEntrega().add(infoEntrega);
        }

        // Agregar el pedido al usuario
        if (usuario.getPedidos() != null) {
            usuario.getPedidos().add(pedido);
        }

        return pedido;
    }

    @Override
    public PedidoDTO toDto(Pedido e) {
        List<DetallePedidoDTO> detalleDTOs = e.getDetallePedidos() != null
                ? e.getDetallePedidos().stream()
                        .map(detallePedidoMapper::toDto)
                        .collect(Collectors.toList())
                : List.of();

        return new PedidoDTO(
                e.getId(),
                e.getFecha(),
                e.getEstado(),
                detalleDTOs,
                e.getTotal(),
                e.getUsuario() != null ? e.getUsuario().getId() : null,
                e.getInformacionDeEntrega() != null && !e.getInformacionDeEntrega().isEmpty()
                        ? informacionDeEntregaMapper.toDto(e.getInformacionDeEntrega().get(0))
                        : null
        );
    }
}
