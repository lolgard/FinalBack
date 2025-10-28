package BakendFinal.entities.models;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@MappedSuperclass
@Getter @Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public abstract class Base {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Builder.Default
    private Boolean eliminado = Boolean.FALSE;

    // marca como eliminado (soft delete)
    public void eliminar() {
        this.eliminado = Boolean.TRUE;
    }

    // restaura (opcional)
    public void restaurar() {
        this.eliminado = Boolean.FALSE;
    }

    // conveniencia
    public boolean isEliminado() {
        return Boolean.TRUE.equals(this.eliminado);
    }
}