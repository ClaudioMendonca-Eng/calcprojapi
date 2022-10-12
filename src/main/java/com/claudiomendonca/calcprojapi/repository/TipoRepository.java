package com.claudiomendonca.calcprojapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.claudiomendonca.calcprojapi.model.Tipo;

@Repository
public interface TipoRepository extends JpaRepository<Tipo, Long> {
    
}
