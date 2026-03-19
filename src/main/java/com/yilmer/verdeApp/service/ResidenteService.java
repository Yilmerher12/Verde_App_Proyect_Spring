package com.yilmer.verdeApp.service;

import com.yilmer.verdeApp.entity.Residente;
import com.yilmer.verdeApp.exception.ResourceNotFoundException;
import com.yilmer.verdeApp.repository.ResidenteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResidenteService {

    // 1. Atributo del repositorio (la herramienta)
    private final ResidenteRepository residenteRepository;

    // 2. Constructor para la Inyección de Dependencias
    public ResidenteService(ResidenteRepository residenteRepository) {
        this.residenteRepository = residenteRepository;
    }
    public List<Residente> obtenerTodos() {
        return residenteRepository.findAll();
    }

    // Metodo para Guardar (Create)
    public Residente guardarResidente(Residente residente) {
        //Tomamos el Id de la unidad (apto y torre) que viene en el body del frontend JSON
        Long idUnidadRecibido = residente.getUnidad().getUnidadId();

        //Preguntamos al repositorio si esa unidad ya está ocupada
        if (residenteRepository.existsByUnidad_UnidadId(idUnidadRecibido)) {
            throw new RuntimeException("Error: La unidad (Torre/Apto) ya tiene un residente asignado.");
        }

        //Si no está ocupada, guardamos normal
        return residenteRepository.save(residente);
    }

    // Metodo para Buscar por ID (Read) con nuestra EXCEPCIÓN
    public Residente obtenerPorId(Long id) {
        return residenteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Residente no encontrado con el ID: " + id));
    }
}