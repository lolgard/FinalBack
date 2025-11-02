package BakendFinal.entities.DTOs.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import BakendFinal.entities.DTOs.productos.*;
import BakendFinal.entities.models.Categoria;
import BakendFinal.entities.models.Producto;
import BakendFinal.repositories.CategoriaRepository;
@Component
public class ProductoMapper implements BaseMapper<Producto,ProductoDTO,ProductoCreate>{
    @Autowired
    private CategoriaRepository categoriaRepository;

    @Override
    public Producto toEntity(ProductoCreate d) {
        Categoria categoria = null;
        if(categoriaRepository.existsById(d.categoriaId())){
            categoria = categoriaRepository.findById(d.categoriaId()).orElse(null);
        }
        return Producto.builder()
                .nombre(d.nombre())
                .precio(d.precio())
                .marca(d.marca())
                .categoria(categoria)
                .imagen(d.imagen())
                .stock(d.stock())
                .build();
    }

    @Override
    public ProductoDTO toDto(Producto e) {
       String categoriaNombre = e.getCategoria() != null ? e.getCategoria().getNombre() : null;
        return new ProductoDTO(
                e.getId(),
                e.getNombre(),
                e.getPrecio(),
                e.getMarca(),
                categoriaNombre,
                e.getImagen(),
                e.getStock()
        );
    }
    
}
