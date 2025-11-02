package BakendFinal.entities.models;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@SuperBuilder
public class Categoria extends Base{
    private String nombre;
    private String descripcion;

    @OneToMany(mappedBy = "categoria")
    @Builder.Default
    private List<Producto> productos = new ArrayList<>();
}
