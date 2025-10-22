package BakendFinal.entities.models;

import BakendFinal.utils.PasswordUtils;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@SuperBuilder
public class Cliente extends Base {

    private String nombre;
    private String email;
    @Setter(AccessLevel.NONE)
    private String pass;

    public void setPass(String pass) {
        this.pass = PasswordUtils.hashPassword(pass);
    }
}
