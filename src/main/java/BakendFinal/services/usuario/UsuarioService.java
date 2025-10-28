package BakendFinal.services.usuario;

import BakendFinal.entities.DTOs.usuario.UsuarioDTO;
import BakendFinal.entities.DTOs.usuario.UsuarioEdit;
import BakendFinal.entities.DTOs.usuario.UsuarioCreate;
import BakendFinal.services.BaseService;

public interface UsuarioService extends BaseService<UsuarioDTO,UsuarioCreate,UsuarioEdit,Long>{
    UsuarioDTO login(String email, String password);
    UsuarioDTO logout(String email);
}
