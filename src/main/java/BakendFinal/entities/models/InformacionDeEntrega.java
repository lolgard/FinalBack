package BakendFinal.entities.models;
import BakendFinal.entities.enums.MetodoDePago;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "informacion_de_entrega")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InformacionDeEntrega extends Base {
    
    @Column(nullable = false)
    private String direccion;
    
    @Column(nullable = false)
    private String telefono;
    

    @Enumerated(EnumType.STRING)   
    private MetodoDePago metodoPago;
    
    @Column(length = 500)
    private String notasAdicionales;
    
    @ManyToOne
    @JoinColumn(name = "pedido_id", nullable = false)
    private Pedido pedido;
}