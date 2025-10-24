package BakendFinal.entities.DTOs.client;

import BakendFinal.entities.enums.Role;

public record ClienteDTO(
    Long id,
    String name,
    String email,
    String pass,
    Role role,
    Boolean loggedIn) {
}
