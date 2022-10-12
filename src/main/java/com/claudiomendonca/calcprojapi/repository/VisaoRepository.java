package com.claudiomendonca.calcprojapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.claudiomendonca.calcprojapi.model.Visao;

@Repository
public interface VisaoRepository extends JpaRepository<Visao, Long> {
    
}
