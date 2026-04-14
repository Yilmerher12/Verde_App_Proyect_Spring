package com.yilmer.verdeApp.controller;
import com.yilmer.verdeApp.entity.ConjuntoResidencial;
import com.yilmer.verdeApp.service.ConjuntoResidencialService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/conjuntos")
public class ConjuntoResidencialController {

    private final ConjuntoResidencialService conjuntoService;

    public ConjuntoResidencialController(ConjuntoResidencialService conjuntoService) {
        this.conjuntoService = conjuntoService;
    }

    @GetMapping
    public ResponseEntity<List<ConjuntoResidencial>> listar() {
        return ResponseEntity.ok(conjuntoService.obtenerTodos());
    }

    @PostMapping
    public ResponseEntity<ConjuntoResidencial> crear(@Valid @RequestBody ConjuntoResidencial conjunto) {
        return ResponseEntity.ok(conjuntoService.guardar(conjunto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConjuntoResidencial> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(conjuntoService.obtenerPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ConjuntoResidencial> actualizar(@PathVariable Long id, @RequestBody ConjuntoResidencial detalles) {

        ConjuntoResidencial existente = conjuntoService.obtenerPorId(id);

        existente.setNombre(detalles.getNombre());
        existente.setDireccion(detalles.getDireccion());

        existente.setNit(detalles.getNit());
        existente.setEstadoSuscripcion(detalles.getEstadoSuscripcion()); // Nuevo

        existente.setFechaProximoPago(detalles.getFechaProximoPago()); // Nuevo

        return ResponseEntity.ok(conjuntoService.guardar(existente));
    }

    @Operation(summary = "Eliminar un conjunto residencial", description = "Borra el conjunto y libera las unidades asociadas")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        conjuntoService.eliminar(id);

        Map<String, String> respuesta = new HashMap<>();
        respuesta.put("status", "success");
        respuesta.put("mensaje", "Conjunto Residencial eliminado exitosamente");
        respuesta.put("id_afectado", id.toString());

        return ResponseEntity.ok(respuesta);
    }
}
