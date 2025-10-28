package BakendFinal.repositories;

import java.util.Optional;

import BakendFinal.entities.models.Categoria;

public interface CategoriaRepository extends  BaseRepository<Categoria, Long>{
    boolean existsByNombre(String nombre);
    Optional<Categoria> findByNombre(String nombre);
}
