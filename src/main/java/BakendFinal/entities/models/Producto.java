package BakendFinal.entities.models;


import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

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
    private String imagen;
    private int stock;

    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;

}
