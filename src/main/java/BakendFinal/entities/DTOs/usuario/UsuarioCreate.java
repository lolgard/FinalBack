package BakendFinal.entities.DTOs.usuario;

import BakendFinal.entities.enums.Role;

public record UsuarioCreate(
    String name,
    String email,
    String pass,
    Role role
) {
}
