package com.yilmer.verdeApp.entity;

import com.yilmer.verdeApp.enums.Rol;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity
@Table(name = "usuarios")
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long usuarioId;

    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @NotBlank(message = "El apellido paterno es obligatorio")
    private String apellidoPaterno;

    private String apellidoMaterno;

    @Email(message = "Email inválido")
    @Column(unique = true, nullable = false)
    private String email;

    @NotBlank(message = "La contraseña es obligatoria")
    private String password;

    @Column(unique = true, nullable = false)
    private String cedula;

    private String telefono;

    @Enumerated(EnumType.STRING)
    private Rol rol;

    @ManyToOne
    @JoinColumn(name = "conjunto_id")
    private ConjuntoResidencial conjunto;

    // Relación 1 a 1: Una unidad física solo puede tener un usuario RESPONSABLE
    @OneToOne
    @JoinColumn(name = "unidad_id", unique = true)
    private Unidad unidad;

    private Integer puntosAcumulados = 0;
}