package com.yilmer.verdeApp.dto;

import com.yilmer.verdeApp.enums.Rol;
import lombok.Data;

@Data
public class UsuarioDTO {
    private Long usuarioId;
    private String nombre;
    private String apellidoPaterno;
    private String email;
    private Rol rol;
    private Integer puntosAcumulados;
    // La unidad y el conjunto (opcionales para el resumen)
    private String nombreConjunto;
}