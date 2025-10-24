package BakendFinal.entities.models;

import BakendFinal.utils.PasswordUtils;
import jakarta.persistence.Entity;
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
public class Cliente extends Base {

    private String name;
    private String email;
    @Setter(AccessLevel.NONE)
    private String pass;

    @Builder.Default
    private Boolean loggedIn = Boolean.FALSE;
    private String role;


    public void setPass(String pass) {
        this.pass = PasswordUtils.hashPassword(pass);
    }



}
