package BakendFinal.entities.DTOs.mapper;

// E: Entidad, D: DTO , DC: DTO Crear
public interface BaseMapper <E,D,DC>{

    public E toEntity(DC d);
    public D toDto(E e);
}
