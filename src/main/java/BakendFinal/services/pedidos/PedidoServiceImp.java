package BakendFinal.services.pedidos;

import org.springframework.beans.factory.annotation.Autowired;

import BakendFinal.entities.DTOs.pedido.PedidoCreate;
import BakendFinal.entities.DTOs.pedido.PedidoDTO;
import BakendFinal.entities.DTOs.pedido.PedidoEdit;
import BakendFinal.entities.DTOs.productos.ProductoDTO;
import BakendFinal.entities.enums.Estado;
import BakendFinal.entities.models.DetallePedido;
import BakendFinal.entities.models.Pedido;
import BakendFinal.services.BaseServiceImp;
import BakendFinal.services.productos.ProductoService;
import org.springframework.stereotype.Service;

@Service
public class PedidoServiceImp extends BaseServiceImp<Pedido, PedidoDTO, PedidoCreate, PedidoEdit, Long> implements PedidoService {

    @Autowired
    ProductoService productoService;

    @Override
    public PedidoDTO actualizar(Long id, PedidoEdit editDto) {
        Pedido pedido = baseRepository.findById(id).orElseThrow(() -> new RuntimeException("Pedido no encontrado"));
        if (editDto.estado() != null) {
            validarEstado(pedido.getEstado(), editDto.estado());
            pedido.setEstado(editDto.estado());
        }
        Pedido pedidoActualizado = baseRepository.save(pedido);
        return baseMapper.toDto(pedidoActualizado);
    }

    private void validarEstado(Estado estadoActual, Estado estadoNuevo) {
        if (estadoActual == Estado.TERMINADO) {
            throw new RuntimeException("No se puede cambiar el estado de un pedido Terminado");
        }
        if (estadoActual == Estado.CANCELADO && estadoNuevo != Estado.CANCELADO) {
            throw new RuntimeException("No se puede cambiar el estado de un pedido Cancelado");
        }
    }

    @Override
    public PedidoDTO crear(PedidoCreate createDto) {
        // Validar que el pedido tenga detalles
        if (createDto.detalles().isEmpty()) {
            throw new RuntimeException("El pedido debe contener al menos un detalle.");
        }
        
        // Validar stock de productos (sin reducir aún)
        createDto.detalles().forEach(detalle -> {
            ProductoDTO producto = productoService.obtenerPorId(detalle.productoId());
            if (producto == null) {
                throw new RuntimeException("Producto no encontrado con ID: " + detalle.productoId());
            } else if (producto.stock() < detalle.cantidad()) {
                throw new RuntimeException("Stock insuficiente para el producto con ID: " + detalle.productoId());
            }
        });
        
        // Convertir PedidoCreate a entidad Pedido
        Pedido pedido = baseMapper.toEntity(createDto);
        
        // Calcular el total del pedido
        Double total = pedido.getDetallePedidos().stream()
                .mapToDouble(DetallePedido::getSubtotal)
                .sum();
        pedido.setTotal(total);
        
        // Guardar el pedido (cascada guardará los detalles)
        Pedido pedidoGuardado = baseRepository.save(pedido);
        
        // Reducir stock de productos DESPUÉS de guardar exitosamente
        createDto.detalles().forEach(detalle -> {
            productoService.reducirStock(detalle.productoId(), detalle.cantidad());
        });
        
        // Convertir a DTO y retornar
        return baseMapper.toDto(pedidoGuardado);
    }
}
