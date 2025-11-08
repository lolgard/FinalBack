package BakendFinal.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import BakendFinal.entities.DTOs.pedido.PedidoCreate;
import BakendFinal.entities.DTOs.pedido.PedidoDTO;
import BakendFinal.entities.DTOs.pedido.PedidoEdit;
import BakendFinal.services.pedidos.PedidoService;

@RestController
@RequestMapping("/pedidos")
public class PedidoController extends BaseController<PedidoDTO, PedidoCreate, PedidoEdit, Long, PedidoService> {
}
