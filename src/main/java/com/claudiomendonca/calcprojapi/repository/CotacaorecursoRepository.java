package com.claudiomendonca.calcprojapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.claudiomendonca.calcprojapi.model.Cotacaorecurso;

@Repository
public interface CotacaorecursoRepository extends JpaRepository<Cotacaorecurso, Long> {
    
}
