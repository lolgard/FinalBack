package BakendFinal.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import BakendFinal.entities.DTOs.usuario.UsuarioDTO;
import BakendFinal.entities.DTOs.usuario.UsuarioEdit;
import BakendFinal.entities.DTOs.usuario.UsuarioLogout;
import BakendFinal.entities.DTOs.usuario.UsuarioPass;
import BakendFinal.services.usuario.UsuarioService;
import BakendFinal.entities.DTOs.usuario.UsuarioCreate;


@RestController
@RequestMapping("/usuario")
public class UsuarioController extends BaseController<UsuarioDTO
,UsuarioCreate,UsuarioEdit,Long,UsuarioService>{

    @PostMapping("/login")
    public ResponseEntity<?> login (@RequestBody UsuarioPass loginDTO){
        try{
            UsuarioDTO usuario = baseService.login(loginDTO.email(), loginDTO.pass());
            return ResponseEntity.ok(usuario);
        }catch(Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PostMapping("/logout")
    public ResponseEntity<?> logout(@RequestBody UsuarioLogout logoutDTO){
        try{
            baseService.logout(logoutDTO.email());
            return ResponseEntity.ok("Logout successful");
        }catch(Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
}
