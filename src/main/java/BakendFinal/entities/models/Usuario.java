package BakendFinal.entities.models;

import BakendFinal.entities.enums.Role;
import BakendFinal.utils.PasswordUtils;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
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

    @Builder.Default
    private Boolean loggedIn = Boolean.FALSE;

    @Enumerated(EnumType.STRING)
    private Role role;


    public void setPass(String pass) {
        this.pass = PasswordUtils.hashPassword(pass);
    }



}
