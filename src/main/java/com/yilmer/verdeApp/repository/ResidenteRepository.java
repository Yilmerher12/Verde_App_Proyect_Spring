package com.yilmer.verdeApp.repository;

import com.yilmer.verdeApp.entity.Residente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
public interface ResidenteRepository extends JpaRepository<Residente, Long> {
    // Spring JPA creará la consulta automáticamente por el nombre del metodo
    boolean existsByUnidad_UnidadId(Long unidadId);
}
