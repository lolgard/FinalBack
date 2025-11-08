package BakendFinal.entities.DTOs.informacionDeEntrega;

import BakendFinal.entities.enums.MetodoDePago;

public record InformacionDeEntregaDTO(
    Long id,
    String direccion,
    String telefono,
    MetodoDePago metodoPago,
    String notasAdicionales,
    Long pedidoId
) {}