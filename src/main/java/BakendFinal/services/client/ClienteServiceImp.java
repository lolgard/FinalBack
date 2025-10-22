package BakendFinal.services.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import BakendFinal.entities.DTOs.client.ClienteCreate;
import BakendFinal.entities.DTOs.client.ClienteDTO;
import BakendFinal.entities.DTOs.client.ClienteEdit;
import BakendFinal.entities.models.Cliente;
import BakendFinal.repositories.ClienteRepository;
import BakendFinal.services.BaseServiceImp;

@Service
public class ClienteServiceImp  extends BaseServiceImp<Cliente,ClienteDTO,ClienteCreate,ClienteEdit,Long> implements ClienteService {
    
    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public ClienteDTO actualizar (Long id, ClienteEdit dto) {
        Cliente cliente = baseRepository.findById(id).orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
        cliente.setNombre(dto.nombre());
        cliente.setEmail(dto.email());
        cliente.setPass(dto.pass());
        Cliente clienteActualizado = baseRepository.save(cliente);
        return baseMapper.toDto(clienteActualizado);

    }
    @Override
    public void login(String email, String password) {
        Cliente cliente = clienteRepository.findByEmail(email)
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        if (!cliente.getPass().equals(password)) {
            throw new RuntimeException("Contraseña incorrecta");
        }
    }
}
