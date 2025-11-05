package BakendFinal.services.pedidos;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import BakendFinal.entities.DTOs.detallePedido.DetallePedidoDTO;
import BakendFinal.entities.DTOs.pedido.PedidoCreate;
import BakendFinal.entities.DTOs.pedido.PedidoDTO;
import BakendFinal.entities.DTOs.pedido.PedidoEdit;
import BakendFinal.entities.models.DetallePedido;
import BakendFinal.entities.models.Pedido;
import BakendFinal.services.BaseServiceImp;
import BakendFinal.services.detallePedido.DetallePedidoService;


public class PedidoServiceImp extends BaseServiceImp<Pedido,PedidoDTO,PedidoCreate,PedidoEdit,Long> implements PedidoService {

    @Autowired
    DetallePedidoService detallePedidoService;
    @Override
    public PedidoDTO actualizar(Long id, PedidoEdit editDto) {
        // TODO Auto-generated method stub
        return super.actualizar(id, editDto);
    }

    @Override
    public PedidoDTO crear(PedidoCreate createDto) {
        List<DetallePedidoDTO> detalles = createDto.detalles().stream().map(detalleCreate->{
            return detallePedidoService.crear(detalleCreate);
        }).collect(Collectors.toList());
        return super.crear(createDto);
    }
    
}
