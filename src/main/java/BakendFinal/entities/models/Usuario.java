package BakendFinal.entities.models;

import java.util.ArrayList;
import java.util.List;

import BakendFinal.entities.enums.Role;
import BakendFinal.utils.PasswordUtils;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToMany;
import lombok.AccessLevel;
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
public class Usuario extends Base {

    private String name;
    
    @Column(unique = true, nullable = false)
    private String email;
    @Setter(AccessLevel.NONE)
    private String pass;

    @lombok.Builder.Default
    private Boolean loggedIn = Boolean.FALSE;

    @Enumerated(EnumType.STRING)
    private Role role;


    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @lombok.Builder.Default
    @JoinTable(name = "usuario_pedidos",
    joinColumns = @JoinColumn(name = "usuario_id"),
    inverseJoinColumns = @JoinColumn(name = "pedido_id"))
    private List<Pedido> pedidos = new ArrayList<>();


    public void setPass(String pass) {
        this.pass = PasswordUtils.hashPassword(pass);
    }



}
