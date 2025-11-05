package BakendFinal.entities.models;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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

public class DetallePedido extends Base {

    private int cantidad;
    private double precioUnitario;
    private double subtotal;

    @OneToMany(cascade = CascadeType.MERGE)
    @JoinColumn(name = "producto_id")
    @lombok.Builder.Default
    private List<Producto> productos = new ArrayList<>();
    @ManyToOne(optional = false)
    @JoinColumn(name = "pedido_id", nullable = false)
    private Pedido pedido;

    private void calcularSubtotal() {
        this.subtotal = this.cantidad * this.precioUnitario;
    }
}
