package BakendFinal.entities.DTOs.mapper;

import org.springframework.stereotype.Component;

import BakendFinal.entities.DTOs.categorias.*;
import BakendFinal.entities.models.Categoria;
@Component
public class CategoriaMapper implements BaseMapper<Categoria,CategoriaDTO,CategoriaCreate>{

    @Override
    public Categoria toEntity(CategoriaCreate d) {
        return Categoria.builder()
                .nombre(d.nombre())
                .descripcion(d.descripcion())
                .build();
    }

    @Override
    public CategoriaDTO toDto(Categoria e) {
        return new CategoriaDTO(
                e.getId(),
                e.getNombre(),
                e.getDescripcion()
        );
    }
}
