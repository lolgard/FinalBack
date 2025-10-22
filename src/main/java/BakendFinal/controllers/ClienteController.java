package BakendFinal.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import BakendFinal.entities.DTOs.client.ClienteCreate;
import BakendFinal.entities.DTOs.client.ClienteDTO;
import BakendFinal.entities.DTOs.client.ClienteEdit;
import BakendFinal.services.client.ClienteService;

@RestController
@RequestMapping("/cliente")
public class ClienteController extends BaseController<ClienteDTO
,ClienteCreate,ClienteEdit,Long,ClienteService>{
    
}
