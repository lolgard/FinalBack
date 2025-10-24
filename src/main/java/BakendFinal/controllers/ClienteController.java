package BakendFinal.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import BakendFinal.entities.DTOs.client.ClienteCreate;
import BakendFinal.entities.DTOs.client.ClienteDTO;
import BakendFinal.entities.DTOs.client.ClienteEdit;
import BakendFinal.entities.DTOs.client.ClienteLogout;
import BakendFinal.entities.DTOs.client.ClientePass;
import BakendFinal.services.client.ClienteService;


@RestController
@RequestMapping("/cliente")
public class ClienteController extends BaseController<ClienteDTO
,ClienteCreate,ClienteEdit,Long,ClienteService>{

    @PostMapping("/login")
    public ResponseEntity<?> login (@RequestBody ClientePass loginDTO){
        try{
            ClienteDTO cliente = baseService.login(loginDTO.email(), loginDTO.pass());
            return ResponseEntity.ok(cliente);
        }catch(Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PostMapping("/logout")
    public ResponseEntity<?> logout(@RequestBody ClienteLogout logoutDTO){
        try{
            baseService.logout(logoutDTO.email());
            return ResponseEntity.ok("Logout successful");
        }catch(Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
}
