package BakendFinal.services.productos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import BakendFinal.entities.DTOs.productos.ProductoCreate;
import BakendFinal.entities.DTOs.productos.ProductoDTO;
import BakendFinal.entities.DTOs.productos.ProductoEdit;
import BakendFinal.entities.models.Producto;
import BakendFinal.repositories.CategoriaRepository;
import BakendFinal.repositories.ProductoRepository;
import BakendFinal.services.BaseServiceImp;
@Service
public class ProductoServiceImp extends BaseServiceImp<Producto, ProductoDTO, ProductoCreate, ProductoEdit, Long> implements ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

	@Override
	public ProductoDTO actualizar(Long id, ProductoEdit editDto) {
        Producto producto = baseRepository.findById(id).orElseThrow(() -> new RuntimeException("Producto no encontrado"));
        producto.setNombre(editDto.nombre());
        producto.setPrecio(editDto.precio());
        baseRepository.save(producto);
        return baseMapper.toDto(producto);
	}

	@Override
	public ProductoDTO crear(ProductoCreate createDto) {
        if (productoRepository.existsByNombre(createDto.nombre()) && productoRepository.existsByMarca(createDto.marca())) {
            throw new RuntimeException("El producto existe o esta eliminado");
        }
        if(createDto.categoriaId() != null && !categoriaRepository.existsById(createDto.categoriaId())){
            throw new RuntimeException("Categoría no existente o eliminada");
        }
		return super.crear(createDto);
	}
}
