package BakendFinal.entities.models;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@SuperBuilder
public class Producto extends Base{
    private String nombre;
    private double precio;
    private String marca;

    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;
}
