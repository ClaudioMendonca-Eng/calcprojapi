package com.claudiomendonca.calcprojapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.claudiomendonca.calcprojapi.model.Recurso;

@Repository
public interface RecursoRepository extends JpaRepository<Recurso, Long> {
    
}
