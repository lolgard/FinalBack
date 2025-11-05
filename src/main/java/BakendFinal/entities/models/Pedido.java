package BakendFinal.entities.models;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import BakendFinal.entities.enums.Estado;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
public class Pedido extends Base {
    
    private LocalDate fecha;

    @Enumerated(EnumType.STRING)
    @lombok.Builder.Default
    private Estado estado = Estado.PENDIENTE;
    @lombok.Builder.Default
    @OneToMany(mappedBy="pedido",cascade=CascadeType.ALL, orphanRemoval=true)
    private List<DetallePedido> detallePedidos = new ArrayList<>();
    private double total;
}
