package com.yilmer.verdeApp.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

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

    private String torre;
    private String numeroApto;

    @ManyToOne
    @JoinColumn(name = "conjunto_id")
    private ConjuntoResidencial conjunto;

}
