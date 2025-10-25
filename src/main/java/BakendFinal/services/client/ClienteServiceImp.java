package BakendFinal.services.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import BakendFinal.entities.DTOs.client.ClienteCreate;
import BakendFinal.entities.DTOs.client.ClienteDTO;
import BakendFinal.entities.DTOs.client.ClienteEdit;
import BakendFinal.entities.models.Cliente;
import BakendFinal.repositories.ClienteRepository;
import BakendFinal.services.BaseServiceImp;
import BakendFinal.utils.PasswordUtils;

@Service
public class ClienteServiceImp extends BaseServiceImp<Cliente, ClienteDTO, ClienteCreate, ClienteEdit, Long> implements ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public ClienteDTO crear(ClienteCreate dto) {
        // Verificar si el email ya existe
        if (clienteRepository.findByEmail(dto.email()).isPresent()) {
            throw new RuntimeException("El correo electrónico ya está registrado");
        }
        // Si no existe, proceder con la creación normal
        return super.crear(dto);
    }

    @Override
    public ClienteDTO actualizar(Long id, ClienteEdit dto) {
        Cliente cliente = baseRepository.findById(id).orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
        cliente.setName(dto.name());
        cliente.setEmail(dto.email());
        cliente.setPass(dto.pass());
        Cliente clienteActualizado = baseRepository.save(cliente);
        return baseMapper.toDto(clienteActualizado);

    }

    @Override
    public ClienteDTO login(String email, String password) {
        Cliente cliente = clienteRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        if (!PasswordUtils.verifyPassword(password, cliente.getPass())) {
            throw new RuntimeException("Contraseña incorrecta");
        }
        cliente.setLoggedIn(true);
        clienteRepository.save(cliente);
        return baseMapper.toDto(cliente);
    }
    @Override
    public ClienteDTO logout(String email) {
        Cliente cliente = clienteRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        cliente.setLoggedIn(false);
        clienteRepository.save(cliente);
        return baseMapper.toDto(cliente);
    }
}