package com.claudiomendonca.calcprojapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.claudiomendonca.calcprojapi.model.Compartilhar;

@Repository
public interface CompartilharRepository extends JpaRepository<Compartilhar, Long> {
    
}
