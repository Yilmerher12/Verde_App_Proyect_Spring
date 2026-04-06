package com.yilmer.verdeApp.repository;

import com.yilmer.verdeApp.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    // Esta es la validación para que no haya dos residentes en la misma torre/apto
    boolean existsByUnidad_UnidadId(Long unidadId);
    Optional<Usuario> findByEmail(String email);

    // Para el Registro: Evita correos duplicados [cite: 465-466]
    boolean existsByEmail(String email);
}
