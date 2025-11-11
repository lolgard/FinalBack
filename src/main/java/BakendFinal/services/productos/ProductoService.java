package BakendFinal.services.productos;
import BakendFinal.entities.DTOs.productos.*;
import BakendFinal.services.BaseService;
public interface ProductoService extends BaseService<ProductoDTO, ProductoCreate, ProductoEdit, Long> {
    void reducirStock(Long productoId, int cantidad);
    void aumentarStock(Long productoId, int cantidad);
}
