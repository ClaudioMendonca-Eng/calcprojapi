package com.claudiomendonca.calcprojapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.claudiomendonca.calcprojapi.model.Empresasistema;

@Repository
public interface EmpresasistemaRepository extends JpaRepository<Empresasistema, Long>{
    
}
