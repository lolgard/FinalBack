package BakendFinal.entities.DTOs.usuario;

import BakendFinal.entities.enums.Role;

public record UsuarioDTO(
    Long id,
    String name,
    String email,
    String pass,
    Role role,
    Boolean loggedIn) {
}
