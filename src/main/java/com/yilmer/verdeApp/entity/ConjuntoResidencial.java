package com.yilmer.verdeApp.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "conjuntos")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ConjuntoResidencial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long conjuntoId;

    @NotBlank(message = "El nombre del conjunto es obligatorio")
    private String nombre;

    @NotBlank(message = "La dirección es necesaria para la ubicación")
    private String direccion;

    @NotBlank(message = "El NIT es obligatorio para temas legales")
    private String nit;
}