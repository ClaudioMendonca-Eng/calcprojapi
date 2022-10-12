package com.claudiomendonca.calcprojapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.claudiomendonca.calcprojapi.model.Comentariocompartilhado;

@Repository
public interface ComentariocompartilhadoRepository extends JpaRepository<Comentariocompartilhado, Long> {
    
}
