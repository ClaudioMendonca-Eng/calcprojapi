package com.claudiomendonca.calcprojapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.claudiomendonca.calcprojapi.model.Orcamento;

@Repository
public interface OrcamentoRepository extends JpaRepository<Orcamento, Long> {
    
}
