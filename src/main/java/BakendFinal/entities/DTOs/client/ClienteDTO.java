package BakendFinal.entities.DTOs.client;

public record ClienteDTO(
    Long id,
    String name,
    String email,
    String pass,
    String role,
    Boolean loggedIn) {
}
