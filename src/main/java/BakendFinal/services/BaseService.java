package BakendFinal.services;

import java.util.List;

// E: Entidad, DC: DTO Crear, DE: DTO Editar, ID: Tipo de dato del ID
public interface BaseService<D, DC, DE, ID> {
    public D crear(DC dtoCrear);
    public D actualizar(ID id, DE dtoActualizar);
    public List<D> listarTodos();
    public void eliminar(ID id);
    public D obtenerPorId(ID id);
}
