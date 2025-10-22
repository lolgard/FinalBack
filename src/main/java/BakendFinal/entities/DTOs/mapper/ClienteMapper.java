package BakendFinal.entities.DTOs.mapper;

import org.springframework.stereotype.Component;

import BakendFinal.entities.DTOs.client.ClienteCreate;
import BakendFinal.entities.DTOs.client.ClienteDTO;
import BakendFinal.entities.models.Cliente;
import BakendFinal.utils.PasswordUtils;

@Component
public class ClienteMapper implements BaseMapper <Cliente,ClienteDTO,ClienteCreate>{
    @Override
    public Cliente toEntity(ClienteCreate dto){
        return Cliente.builder()
                .nombre(dto.nombre())
                .pass(PasswordUtils.hashPassword(dto.pass())) // <-- Hashea aquí
                .email(dto.email())
                .build();
    }
    @Override
    public ClienteDTO toDto(Cliente entity){
        return new ClienteDTO(
                entity.getNombre(),
                entity.getEmail(),
                entity.getPass()
        );
    }
}
