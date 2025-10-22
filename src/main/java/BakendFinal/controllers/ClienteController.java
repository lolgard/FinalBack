package BakendFinal.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import BakendFinal.entities.DTOs.client.ClienteCreate;
import BakendFinal.entities.DTOs.client.ClienteDTO;
import BakendFinal.entities.DTOs.client.ClienteEdit;
import BakendFinal.entities.DTOs.client.ClientePass;
import BakendFinal.services.client.ClienteService;

import org.springframework.web.bind.annotation.PostMapping;


@RestController
@RequestMapping("/cliente")
public class ClienteController extends BaseController<ClienteDTO
,ClienteCreate,ClienteEdit,Long,ClienteService>{

    @PostMapping("/login")
    public ResponseEntity<?> login (@RequestBody ClientePass login){
        try{
            baseService.login(login.email(), login.pass());
            return ResponseEntity.ok().build();
        }catch(Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
