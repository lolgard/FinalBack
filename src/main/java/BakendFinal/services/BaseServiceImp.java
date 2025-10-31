package BakendFinal.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import BakendFinal.entities.DTOs.mapper.BaseMapper;
import BakendFinal.entities.models.Base;
import BakendFinal.repositories.BaseRepository;

public abstract class BaseServiceImp <E extends Base,D,DC,DE,ID> implements BaseService<D,DC,DE,ID>{

    @Autowired
    protected BaseRepository<E,ID> baseRepository;

    @Autowired
    protected BaseMapper<E,D,DC> baseMapper;

    @Override
    public D crear(DC createDto) {
        E entity = baseMapper.toEntity(createDto);
        E savedEntity = baseRepository.save(entity);
        return baseMapper.toDto(savedEntity);
    }

    @Override
    public D actualizar(ID id, DE editDto) {
        // Implementación básica que debe ser sobrescrita en cada servicio específico
        throw new UnsupportedOperationException("Edit method needs specific implementation in each service");
    }

    @Override
    public List<D> listarTodos() {
        List<E> entities = baseRepository.findAll();
        return entities.stream()
                .filter(e -> {
                    if (e instanceof Base base) {
                        return !base.isEliminado();
                    }
                    return true;
                })
                .map(baseMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public D obtenerPorId(ID id) {
        Optional<E> entity = baseRepository.findById(id);
        if (entity.isEmpty()) return null;
        E e = entity.get();
        if (e instanceof Base && ((Base) e).isEliminado()) return null;
        return baseMapper.toDto(e);
    }

    @Override
    public void eliminar(ID id) {
        Optional<E> entityOpt = baseRepository.findById(id);
        if (entityOpt.isEmpty()) {
            throw new RuntimeException("Entidad no encontrada");
        }
        E entity = entityOpt.get();
            entity.eliminar();
            baseRepository.save(entity);
    }
}
