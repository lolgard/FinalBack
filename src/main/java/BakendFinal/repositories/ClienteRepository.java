package BakendFinal.repositories;

import java.util.Optional;

import BakendFinal.entities.models.Cliente;

public interface ClienteRepository extends BaseRepository<Cliente,Long>{
    Optional<Cliente> findByEmail(String email);
}
