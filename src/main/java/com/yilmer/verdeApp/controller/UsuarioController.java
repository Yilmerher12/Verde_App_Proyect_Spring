package com.yilmer.verdeApp.controller;

import com.yilmer.verdeApp.dto.UsuarioDTO;
import com.yilmer.verdeApp.entity.Usuario;
import com.yilmer.verdeApp.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> listar() {
        return ResponseEntity.ok(usuarioService.obtenerTodos());
    }

    @PostMapping
    public ResponseEntity<Usuario> crear(@Valid @RequestBody Usuario usuario) {
        return ResponseEntity.ok(usuarioService.guardarUsuario(usuario));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> obtenerDetalle(@PathVariable Long id) {
        Usuario u = usuarioService.obtenerPorId(id);

        // Convertimos la Entidad en un DTO "Limpio"
        UsuarioDTO dto = new UsuarioDTO();
        dto.setUsuarioId(u.getUsuarioId());
        dto.setNombre(u.getNombre());
        dto.setEmail(u.getEmail());
        dto.setRol(u.getRol());
        dto.setPuntosAcumulados(u.getPuntosAcumulados());

        return ResponseEntity.ok(dto);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Usuario> actualizar(@PathVariable Long id, @RequestBody Usuario detalles) {
        return ResponseEntity.ok(usuarioService.actualizar(id, detalles));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        usuarioService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}