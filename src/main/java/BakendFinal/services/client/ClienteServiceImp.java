package BakendFinal.services.client;

import BakendFinal.entities.DTOs.client.ClienteCreate;
import BakendFinal.entities.DTOs.client.ClienteDTO;
import BakendFinal.entities.DTOs.client.ClienteEdit;
import BakendFinal.entities.models.Cliente;
import BakendFinal.services.BaseServiceImp;

public class ClienteServiceImp  extends BaseServiceImp<Cliente,ClienteDTO,ClienteCreate,ClienteEdit,Long> implements ClienteService {
    @Override
    public ClienteDTO actualizar (Long id, ClienteEdit dto) {
        Cliente cliente = baseRepository.findById(id).orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
        cliente.setNombre(dto.nombre());
        cliente.setEmail(dto.email());
        cliente.setPass(dto.pass());
        Cliente clienteActualizado = baseRepository.save(cliente);
        return baseMapper.toDto(clienteActualizado);

    }
}
