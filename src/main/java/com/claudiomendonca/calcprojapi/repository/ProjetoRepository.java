package com.claudiomendonca.calcprojapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.claudiomendonca.calcprojapi.model.Projeto;

@Repository
public interface ProjetoRepository extends JpaRepository<Projeto, Long> {
    
}
