package com.yilmer.verdeApp.controller;

import com.yilmer.verdeApp.entity.Usuario;
import com.yilmer.verdeApp.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UsuarioService usuarioService;

    public AuthController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("/registro")
    public ResponseEntity<?> registrar(@Valid @RequestBody Usuario usuario) {
        Usuario guardado = usuarioService.guardarUsuario(usuario);

        Map<String, String> respuesta = new HashMap<>();
        respuesta.put("mensaje", "Usuario registrado correctamente");
        respuesta.put("email", guardado.getEmail());
        // Cumplimos con la excelencia: No devolvemos el objeto Usuario completo para ocultar la clave [cite: 510-513]
        return ResponseEntity.ok(respuesta);
    }

    @GetMapping("/test")
    public String test() {
        return "✅ La portería (Auth) está funcionando";
    }
}