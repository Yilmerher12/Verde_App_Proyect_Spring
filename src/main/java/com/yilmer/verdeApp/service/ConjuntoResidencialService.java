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

    public ConjuntoResidencial guardar(ConjuntoResidencial conjunto) {
        // Aquí podríamos validar que el NIT no esté repetido antes de guardar
        return conjuntoRepository.save(conjunto);
    }

    public ConjuntoResidencial obtenerPorId(Long id) {
        return conjuntoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Conjunto no encontrado con ID: " + id));
    }
    public void eliminar(Long id) {
        // 1. Verificamos si existe antes de intentar borrar.
        // Si no existe, obtenerPorId lanzará la ResourceNotFoundException automáticamente.
        this.obtenerPorId(id);

        // 2. Si pasó la verificación, procedemos con el borrado físico.
        conjuntoRepository.deleteById(id);
    }
}
