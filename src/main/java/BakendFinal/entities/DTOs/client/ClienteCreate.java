package BakendFinal.entities.DTOs.client;

public record ClienteCreate(
    String name,
    String email,
    String pass,
    String role
) {
}
