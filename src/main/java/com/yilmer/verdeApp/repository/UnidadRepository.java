package com.yilmer.verdeApp.repository;

import  com.yilmer.verdeApp.entity.Unidad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface UnidadRepository extends JpaRepository<Unidad, Long> {
}
