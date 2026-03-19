package com.yilmer.verdeApp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "usuarios")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Residente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long residentId;

    //Las anotacionees @NotBlank (No vacio) y @Email (formato de email) son validaciones que agregamos desde Spring
    @NotBlank(message = "El nombre no puede estar vacío")
    private String nombre;

    @NotBlank(message = "El primer apellido no puede estar vacío")
    private String primerApellido;
    private String segundoApellido;

    //La columba de cedula debe ser unica en la tabla y no puede ser null o no tener ningun valor
    @Column(unique = true, nullable = false)
    private String cedula;

    @Email(message = "Formato de correo inválido")
    @NotBlank(message = "El email no puede estar vacío")
    private String email;

    //Solo un residente pertenece a una unidad (apto y torre)
    //unique = true quiere decir que debe ser unico
    @OneToOne
    @JoinColumn(name = "unidad_id", unique = true)
    private Unidad unidad;
}
