package com.yilmer.verdeApp.entity;

import com.yilmer.verdeApp.enums.EstadoSuscripcion;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

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

    private String nombre;
    private String nit;
    private String direccion;

    @Enumerated(EnumType.STRING)
    private EstadoSuscripcion estadoSuscripcion = EstadoSuscripcion.DEMO; // Inicia en DEMO por defecto

    private LocalDate fechaProximoPago;
}