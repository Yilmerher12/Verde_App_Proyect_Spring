package com.yilmer.verdeApp.service;

import com.yilmer.verdeApp.entity.Unidad;
import com.yilmer.verdeApp.exception.ResourceNotFoundException;
import com.yilmer.verdeApp.repository.UnidadRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UnidadService {
    private final UnidadRepository unidadRepository;

    public UnidadService(UnidadRepository unidadRepository) {
        this.unidadRepository = unidadRepository;
    }

    public List<Unidad> obtenerTodas() {
        return unidadRepository.findAll();
    }

    public Unidad obtenerPorId(Long id) {
        return unidadRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Unidad no encontrada con ID: " + id));
    }

    public Unidad guardar(Unidad unidad) {
        return unidadRepository.save(unidad);
    }

    public void eliminar(Long id) {
        // Primero verificamos si existe para lanzar nuestra excepción personalizada si no
        this.obtenerPorId(id);
        unidadRepository.deleteById(id);
    }
}
