package com.yilmer.verdeApp.controller;

import com.yilmer.verdeApp.entity.Unidad;
import com.yilmer.verdeApp.service.UnidadService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/unidades")
public class UnidadController {

    private final UnidadService unidadService;

    public UnidadController(UnidadService unidadService) {
        this.unidadService = unidadService;
    }

    @GetMapping
    public ResponseEntity<List<Unidad>> listarTodas() {
        return ResponseEntity.ok(unidadService.obtenerTodas());
    }

    @PostMapping
    public ResponseEntity<Unidad> crear(@Valid @RequestBody Unidad unidad) {
        return ResponseEntity.ok(unidadService.guardar(unidad));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Unidad> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(unidadService.obtenerPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Unidad> actualizar(@PathVariable Long id, @RequestBody Unidad detalles) {

        Unidad existente = unidadService.obtenerPorId(id);

        existente.setTorre(detalles.getTorre());
        existente.setNumeroApto(detalles.getNumeroApto()); // Nombre corregido

        return ResponseEntity.ok(unidadService.guardar(existente));
    }

    @Operation(summary = "Eliminar una unidad habitacional", description = "Elimina el registro de la unidad física (apartamento/casa)")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        unidadService.eliminar(id);

        Map<String, String> respuesta = new HashMap<>();
        respuesta.put("status", "success");
        respuesta.put("mensaje", "Unidad habitacional eliminada del sistema");

        return ResponseEntity.ok(respuesta);
    }
}