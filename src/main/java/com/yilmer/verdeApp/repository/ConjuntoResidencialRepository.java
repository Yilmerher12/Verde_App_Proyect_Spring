package com.yilmer.verdeApp.repository;
import com.yilmer.verdeApp.entity.ConjuntoResidencial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConjuntoResidencialRepository extends JpaRepository <ConjuntoResidencial, Long>{
}
