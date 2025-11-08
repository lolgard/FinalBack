package BakendFinal.entities.DTOs.informacionDeEntrega;

public record InformacionDeEntregaCreate(
    
    String direccion,
    String telefono,
    String metodoPago,
    String notasAdicionales
    
) {}
