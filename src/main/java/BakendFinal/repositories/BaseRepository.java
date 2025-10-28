package BakendFinal.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;
// E: Entidad, ID: Tipo de dato del ID
@NoRepositoryBean
@Repository
public interface BaseRepository<E,ID> extends JpaRepository<E, ID> {
}
