package com.claudiomendonca.calcprojapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.claudiomendonca.calcprojapi.model.Comentarioprivado;

@Repository
public interface ComentarioprivadoRepository extends JpaRepository<Comentarioprivado, Long> {
    
}
