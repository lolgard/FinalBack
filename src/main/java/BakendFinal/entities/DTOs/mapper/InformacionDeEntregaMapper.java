package BakendFinal.entities.DTOs.mapper;

import org.springframework.stereotype.Component;

import BakendFinal.entities.DTOs.informacionDeEntrega.InformacionDeEntregaCreate;
import BakendFinal.entities.enums.MetodoDePago;
import BakendFinal.entities.DTOs.informacionDeEntrega.InformacionDeEntregaDTO;
import BakendFinal.entities.models.InformacionDeEntrega;

@Component
public class InformacionDeEntregaMapper implements BaseMapper<InformacionDeEntrega, InformacionDeEntregaDTO, InformacionDeEntregaCreate> {

    @Override
    public InformacionDeEntrega toEntity(InformacionDeEntregaCreate dto) {
        return InformacionDeEntrega.builder()
                .direccion(dto.direccion())
                .telefono(dto.telefono())
                .metodoPago(MetodoDePago.valueOf(dto.metodoPago()))
                .notasAdicionales(dto.notasAdicionales())
                .build();
    }
    
    public InformacionDeEntregaDTO toDto(InformacionDeEntrega entity) {
        return new InformacionDeEntregaDTO(
                entity.getId(),
                entity.getDireccion(),
                entity.getTelefono(),
                entity.getMetodoPago(),
                entity.getNotasAdicionales(),
                entity.getPedido() != null ? entity.getPedido().getId() : null
        );
    }
}