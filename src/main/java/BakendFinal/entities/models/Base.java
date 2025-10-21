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


}