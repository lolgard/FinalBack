package BakendFinal.entities.DTOs.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import BakendFinal.entities.DTOs.detallePedido.DetallePedidoCreate;
import BakendFinal.entities.DTOs.detallePedido.DetallePedidoDTO;
import BakendFinal.entities.models.DetallePedido;
import BakendFinal.entities.models.Pedido;
import BakendFinal.entities.models.Producto;
import BakendFinal.repositories.PedidoRepository;
import BakendFinal.repositories.ProductoRepository;

@Component
public class DetallePedidoMapper implements BaseMapper<DetallePedido, DetallePedidoDTO, DetallePedidoCreate> {

    @Autowired
    private ProductoRepository productoRepository;

    @Override
    public DetallePedido toEntity(DetallePedidoCreate d) {
        Producto producto = productoRepository.findById(d.productoId()).orElse(null);
        if (producto == null) {
            throw new RuntimeException("Producto no encontrado con ID: " + d.productoId());
        }
        return DetallePedido.builder()
                .cantidad(d.cantidad())
                .producto(producto)
                .subtotal(producto.getPrecio() * d.cantidad())
                .build();
    }

    @Override
    public DetallePedidoDTO toDto(DetallePedido e) {
        return new DetallePedidoDTO(
                e.getId(),
                e.getProducto().getId(),
                e.getCantidad(),
                e.getSubtotal()
        );
    }

}
