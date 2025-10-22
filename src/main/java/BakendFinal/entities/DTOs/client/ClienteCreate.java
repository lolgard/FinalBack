package BakendFinal.entities.DTOs.client;

public record ClienteCreate(
    String nombre,
    String email,
    String pass
) {
}
