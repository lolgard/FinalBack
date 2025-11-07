package BakendFinal.services.detallePedido;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import BakendFinal.entities.DTOs.detallePedido.DetallePedidoCreate;
import BakendFinal.entities.DTOs.detallePedido.DetallePedidoDTO;
import BakendFinal.entities.DTOs.detallePedido.DetallePedidoEdit;
import BakendFinal.entities.models.DetallePedido;
import BakendFinal.entities.models.Pedido;
import BakendFinal.entities.models.Producto;
import BakendFinal.repositories.PedidoRepository;
import BakendFinal.repositories.ProductoRepository;
import BakendFinal.services.BaseServiceImp;

public class DetallePedidoServiceImp extends BaseServiceImp<DetallePedido,DetallePedidoDTO,DetallePedidoCreate,DetallePedidoEdit,Long> implements DetallePedidoService{

    @Autowired
    ProductoRepository productoRepository;
    @Autowired
    PedidoRepository pedidoRepository;
    @Override
    public DetallePedidoDTO actualizar(Long id, DetallePedidoEdit editDto) {
        Producto producto = productoRepository.findById(editDto.productoId()).orElse(null);
        if(producto == null){
            throw new RuntimeException("Producto no encontrado con ID: " + editDto.productoId());
        }
        return super.actualizar(id, editDto);
    }

    @Override
    public DetallePedidoDTO crear(DetallePedidoCreate createDto) {
        Producto producto = productoRepository.findById(createDto.productoId()).orElse(null);
        if(producto == null){
            throw new RuntimeException("Producto no encontrado con ID: " + createDto.productoId());
        }else if(producto.getStock() < createDto.cantidad()){
            throw new RuntimeException("Stock insuficiente para el producto con ID: " + createDto.productoId());
        }
        return super.crear(createDto);
    }

}
    

