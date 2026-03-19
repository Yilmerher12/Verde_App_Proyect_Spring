package com.yilmer.verdeApp.controller;

import com.yilmer.verdeApp.entity.Residente;
import com.yilmer.verdeApp.service.ResidenteService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/residentes") // La URL base para Postman
public class ResidenteController {

    private final ResidenteService residenteService;

    // Inyección de dependencias por constructor
    public ResidenteController(ResidenteService residenteService) {
        this.residenteService = residenteService;
    }

    // Endpoint para CREAR un residente
    @PostMapping
    public ResponseEntity<Residente> crear(@Valid @RequestBody Residente residente) {
        return ResponseEntity.ok(residenteService.guardarResidente(residente));
    }

    // Endpoint para BUSCAR por ID
    @GetMapping("/{id}")
    public ResponseEntity<Residente> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(residenteService.obtenerPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Residente> actualizar(@Valid @PathVariable Long id, @RequestBody Residente residenteDetalles) {
        // 1. Buscamos si existe (usando el Service que ya tiene la excepción)
        Residente residenteExistente = residenteService.obtenerPorId(id);

        // 2. Actualizamos los datos del objeto que encontramos con lo que llega de Postman
        residenteExistente.setNombre(residenteDetalles.getNombre());
        residenteExistente.setEmail(residenteDetalles.getEmail());
        residenteExistente.setCedula(residenteDetalles.getCedula());
        residenteExistente.setPrimerApellido(residenteDetalles.getPrimerApellido());
        residenteExistente.setSegundoApellido(residenteDetalles.getSegundoApellido());
        // (Aquí usamos los setters que Lombok creó por nosotros)

        // 3. Guardamos los cambios
        return ResponseEntity.ok(residenteService.guardarResidente(residenteExistente));
    }
    @GetMapping
    public ResponseEntity<List<Residente>> listarTodos() {
        return ResponseEntity.ok(residenteService.obtenerTodos());
    }
}