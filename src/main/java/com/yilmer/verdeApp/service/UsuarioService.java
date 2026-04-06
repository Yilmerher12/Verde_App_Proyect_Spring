package com.yilmer.verdeApp.service;

import com.yilmer.verdeApp.entity.Usuario;
import com.yilmer.verdeApp.enums.Rol;
import com.yilmer.verdeApp.exception.ResourceNotFoundException;
import com.yilmer.verdeApp.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public List<Usuario> obtenerTodos() {
        return usuarioRepository.findAll();
    }

    public Usuario guardarUsuario(Usuario usuario) {
        // REGLA DE NEGOCIO: Si es residente, verificar que la unidad no esté ocupada
        if (usuario.getRol() == Rol.RESIDENTE && usuario.getUnidad() != null) {
            Long idUnidad = usuario.getUnidad().getUnidadId();
            if (usuarioRepository.existsByUnidad_UnidadId(idUnidad)) {
                throw new RuntimeException("Error: Esta unidad ya tiene un residente asignado.");
            }
        }

        // Aquí podrías añadir lógica para encriptar la contraseña más adelante
        return usuarioRepository.save(usuario);
    }

    public Usuario obtenerPorId(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con ID: " + id));
    }
    public Usuario actualizar(Long id, Usuario detalles) {
        // 1. Verificar si existe (Lanza excepción si no)
        Usuario usuarioExistente = this.obtenerPorId(id);

        // 2. Modificar solo los campos permitidos
        usuarioExistente.setNombre(detalles.getNombre());
        usuarioExistente.setApellidoPaterno(detalles.getApellidoPaterno());
        usuarioExistente.setApellidoMaterno(detalles.getApellidoMaterno());
        usuarioExistente.setTelefono(detalles.getTelefono());
        usuarioExistente.setEmail(detalles.getEmail());

        // Si cambia de unidad o conjunto, aquí se validaría la lógica de negocio
        if (detalles.getUnidad() != null) usuarioExistente.setUnidad(detalles.getUnidad());

        return usuarioRepository.save(usuarioExistente);
    }

    public void eliminar(Long id) {
        // Verificamos existencia antes de proceder
        this.obtenerPorId(id);
        usuarioRepository.deleteById(id);
    }
}