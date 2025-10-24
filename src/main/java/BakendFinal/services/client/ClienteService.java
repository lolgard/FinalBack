package BakendFinal.services.client;

import BakendFinal.entities.DTOs.client.ClienteCreate;
import BakendFinal.entities.DTOs.client.ClienteDTO;
import BakendFinal.entities.DTOs.client.ClienteEdit;
import BakendFinal.services.BaseService;

public interface ClienteService extends BaseService<ClienteDTO,ClienteCreate,ClienteEdit,Long>{
    ClienteDTO login(String email, String password);
    ClienteDTO logout(String email);
}
