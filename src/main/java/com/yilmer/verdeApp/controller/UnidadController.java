package com.yilmer.verdeApp.controller;

import com.yilmer.verdeApp.entity.Unidad;
import com.yilmer.verdeApp.service.UnidadService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    // Método para ACTUALIZAR (PUT)
    @PutMapping("/{id}")
    public ResponseEntity<Unidad> actualizar(@Valid @PathVariable Long id, @RequestBody Unidad unidadDetalles) {
        // 1. Buscamos la unidad. Si no existe, salta la alarma (Exception).
        Unidad unidadExistente = unidadService.obtenerPorId(id);

        // 2. Modificamos los datos
        unidadExistente.setTorre(unidadDetalles.getTorre());
        unidadExistente.setNumeroApartamento(unidadDetalles.getNumeroApartamento());

        // 3. Guardamos los cambios
        return ResponseEntity.ok(unidadService.guardar(unidadExistente));
    }

    // Método para BORRAR (DELETE)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        // Primero verificamos que exista para poder lanzar el error si no
        unidadService.obtenerPorId(id);

        // Aquí deberías tener un método borrar en tu UnidadService
        // unidadService.eliminar(id);

        return ResponseEntity.noContent().build(); // Retorna un 204 (Sin contenido)
    }
}