package BakendFinal.services.categorias;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import BakendFinal.entities.DTOs.categorias.CategoriaCreate;
import BakendFinal.entities.DTOs.categorias.CategoriaDTO;
import BakendFinal.entities.DTOs.categorias.CategoriaEdit;
import BakendFinal.entities.models.Categoria;
import BakendFinal.repositories.CategoriaRepository;
import BakendFinal.services.BaseServiceImp;
@Service
public class CategoriaServiceImp extends BaseServiceImp<Categoria, CategoriaDTO, CategoriaCreate, CategoriaEdit, Long> implements CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;
	@Override
	public CategoriaDTO crear(CategoriaCreate createDto) {
        if(categoriaRepository.existsByNombre(createDto.nombre())){
            throw new RuntimeException("La categoría ya existe y si no esta eliminada");
        }
		return super.crear(createDto);
	}

	@Override
	public CategoriaDTO actualizar(Long id, CategoriaEdit editDto) {
        Categoria categoria = baseRepository.findById(id).orElseThrow(() -> new RuntimeException("Categoría no encontrada"));
        categoria.setNombre(editDto.nombre());
        categoria.setDescripcion(editDto.descripcion());
        Categoria categoriaActualizada = baseRepository.save(categoria);
        return baseMapper.toDto(categoriaActualizada);
	}
}
