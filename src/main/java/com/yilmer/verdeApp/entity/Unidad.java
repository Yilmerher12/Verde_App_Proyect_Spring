package com.yilmer.verdeApp.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "unidades")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Unidad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long unidadId;

    @NotBlank(message = "Debe especificar la torre o bloque")
    private String torre;

    @NotBlank(message = "El número de apartamento no puede estar vacío")
    private String numeroApartamento;

    @NotNull(message = "La unidad debe pertenecer a un conjunto")
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "conjunto_id")
    private ConjuntoResidencial conjunto;

}
