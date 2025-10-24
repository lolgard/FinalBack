package BakendFinal.entities.DTOs.client;

import BakendFinal.entities.enums.Role;

public record ClienteCreate(
    String name,
    String email,
    String pass,
    Role role
) {
}
