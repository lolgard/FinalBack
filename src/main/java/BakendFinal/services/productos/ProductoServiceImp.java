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
        producto.setImagen(editDto.imagen());
        producto.setStock(editDto.stock());
        if(editDto.categoriaId() != null){
            if(!categoriaRepository.existsById(editDto.categoriaId())){
                throw new RuntimeException("Categoría no existente o eliminada");
            }
            producto.setCategoria(categoriaRepository.findById(editDto.categoriaId()).get());
        }
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

    @Override
    public void reducirStock(Long productoId, int cantidad) {
        Producto producto = productoRepository.findById(productoId)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con ID: " + productoId));

        if (producto.getStock() < cantidad) {
            throw new RuntimeException("Stock insuficiente para el producto con ID: " + productoId);
        }

        producto.setStock(producto.getStock() - cantidad);
        productoRepository.save(producto);
    }
}
