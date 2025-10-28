package BakendFinal.entities.DTOs.mapper;

import org.springframework.stereotype.Component;

import BakendFinal.entities.DTOs.usuario.UsuarioDTO;
import BakendFinal.entities.DTOs.usuario.UsuarioCreate;
import BakendFinal.entities.models.Usuario;
import BakendFinal.utils.PasswordUtils;
@Component
public class UsuarioMapper implements BaseMapper <Usuario,UsuarioDTO,UsuarioCreate>{
    @Override
    public Usuario toEntity(UsuarioCreate dto){
        return Usuario.builder()
                .name(dto.name())
                .pass(PasswordUtils.hashPassword(dto.pass()))
                .email(dto.email())
                .role(dto.role())
                .build();
    }
    @Override
    public UsuarioDTO toDto(Usuario entity){
        return new UsuarioDTO(
                entity.getId(),
                entity.getName(),
                entity.getEmail(),
                entity.getPass(),
                entity.getRole(),
                entity.getLoggedIn()
        );
    }
}
