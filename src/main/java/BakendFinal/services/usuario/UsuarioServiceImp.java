package BakendFinal.services.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import BakendFinal.entities.DTOs.usuario.UsuarioDTO;
import BakendFinal.entities.DTOs.usuario.UsuarioEdit;
import BakendFinal.entities.DTOs.usuario.UsuarioCreate;
import BakendFinal.entities.models.Usuario;
import BakendFinal.repositories.UsuarioRepository;
import BakendFinal.services.BaseServiceImp;
import BakendFinal.utils.PasswordUtils;

@Service
public class UsuarioServiceImp extends BaseServiceImp<Usuario, UsuarioDTO, UsuarioCreate, UsuarioEdit, Long> implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UsuarioDTO crear(UsuarioCreate dto) {
        // Verificar si el email ya existe
        if (usuarioRepository.existsByEmail(dto.email())) {
            throw new RuntimeException("Datos no disponibles");
        }
        // Si no existe, proceder con la creación normal
        return super.crear(dto);
    }

    @Override
    public UsuarioDTO actualizar(Long id, UsuarioEdit dto) {
        Usuario usuario = baseRepository.findById(id).orElseThrow(() -> new RuntimeException("Credenciales incorrectas"));
        usuario.setName(dto.name());
        usuario.setEmail(dto.email());
        usuario.setPass(dto.pass());
        Usuario usuarioActualizado = baseRepository.save(usuario);
        return baseMapper.toDto(usuarioActualizado);

    }

    @Override
    public UsuarioDTO login(String email, String password) {
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        if (!PasswordUtils.verifyPassword(password, usuario.getPass())) {
            throw new RuntimeException("Contraseña incorrecta");
        }
        usuario.setLoggedIn(true);
        usuarioRepository.save(usuario);
        return baseMapper.toDto(usuario);
    }
    @Override
    public UsuarioDTO logout(String email) {
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        usuario.setLoggedIn(false);
        usuarioRepository.save(usuario);
        return baseMapper.toDto(usuario);
    }
}