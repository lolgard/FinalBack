package BakendFinal.repositories;

import java.util.Optional;

import BakendFinal.entities.models.Usuario;

public interface UsuarioRepository extends BaseRepository<Usuario,Long>{
    Optional<Usuario> findByEmail(String email); //Creado para el login
    boolean existsByEmail(String email);
}
