package BakendFinal.entities.DTOs.mapper;

import org.springframework.beans.factory.annotation.Autowired;

import BakendFinal.entities.DTOs.detallePedido.DetallePedidoCreate;
import BakendFinal.entities.DTOs.detallePedido.DetallePedidoDTO;
import BakendFinal.entities.models.DetallePedido;
import BakendFinal.entities.models.Pedido;
import BakendFinal.entities.models.Producto;
import BakendFinal.repositories.PedidoRepository;
import BakendFinal.repositories.ProductoRepository;

public class DetallePedidoMapper implements BaseMapper<DetallePedido, DetallePedidoDTO, DetallePedidoCreate> {

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private PedidoRepository pedidoRepository;

    @Override
    public DetallePedido toEntity(DetallePedidoCreate d) {
        Producto producto = productoRepository.findById(d.productoId()).orElse(null);
        if (producto == null) {
            throw new IllegalArgumentException("Producto no encontrado con ID: " + d.productoId());
        }
        Pedido pedido = pedidoRepository.findById(d.pedidoId()).orElse(null);
        
        return DetallePedido.builder()
                .cantidad(d.cantidad())
                .pedido(pedido)
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

    // Metodo para manejar la relacion con Pedido al crear DetallePedido
    public DetallePedido toEntity(DetallePedidoCreate d, Pedido pedido) {
        Producto producto = productoRepository.findById(d.productoId()).orElse(null);
        if (producto == null) {
            throw new IllegalArgumentException("Producto no encontrado con ID: " + d.productoId());
        }

        return DetallePedido.builder()
                .cantidad(d.cantidad())
                .pedido(pedido)
                .producto(producto)
                .subtotal(producto.getPrecio() * d.cantidad())
                .build();
    }

}
