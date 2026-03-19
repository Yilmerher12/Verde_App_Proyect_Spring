package com.yilmer.verdeApp.service;

import com.yilmer.verdeApp.entity.ConjuntoResidencial;
import com.yilmer.verdeApp.exception.ResourceNotFoundException;
import com.yilmer.verdeApp.repository.ConjuntoResidencialRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConjuntoResidencialService {
    private final ConjuntoResidencialRepository conjuntoRepository;

    public ConjuntoResidencialService(ConjuntoResidencialRepository conjuntoRepository) {
        this.conjuntoRepository = conjuntoRepository;
    }

    public List<ConjuntoResidencial> obtenerTodos() {
        return conjuntoRepository.findAll();
    }

    public ConjuntoResidencial obtenerPorId(Long id) {
        return conjuntoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Conjunto no encontrado con ID: " + id));
    }

    public ConjuntoResidencial guardar(ConjuntoResidencial conjunto) {
        return conjuntoRepository.save(conjunto);
    }

    public void eliminar(Long id) {
        // Verificamos si existe antes de borrar para que salte nuestra excepción personalizada
        this.obtenerPorId(id);
        conjuntoRepository.deleteById(id);
    }
}
