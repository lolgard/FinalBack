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
                .name(dto.name())
                .pass(PasswordUtils.hashPassword(dto.pass()))
                .email(dto.email())
                .role(dto.role())
                .build();
    }
    @Override
    public ClienteDTO toDto(Cliente entity){
        return new ClienteDTO(
                entity.getId(),
                entity.getName(),
                entity.getEmail(),
                entity.getPass(),
                entity.getRole(),
                entity.getLoggedIn()
        );
    }
}
