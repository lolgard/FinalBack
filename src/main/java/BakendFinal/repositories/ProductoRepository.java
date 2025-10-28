package BakendFinal.repositories;


import BakendFinal.entities.models.Producto;

public interface ProductoRepository extends BaseRepository<Producto, Long> {
    boolean existsByNombre(String nombre);
    boolean existsByMarca(String marca);
}
