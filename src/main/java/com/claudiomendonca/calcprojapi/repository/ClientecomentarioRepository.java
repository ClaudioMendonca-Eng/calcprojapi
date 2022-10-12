package com.claudiomendonca.calcprojapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.claudiomendonca.calcprojapi.model.Clientecomentario;

@Repository
public interface ClientecomentarioRepository extends JpaRepository<Clientecomentario, Long> {
    
}
