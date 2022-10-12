package com.claudiomendonca.calcprojapi.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Data
@Table(name = "comentarioprivado")
public class Comentarioprivado {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
	private String comentario;

    @JsonIgnore
    @ManyToOne
	@JoinColumn(name = "projeto_id")
    private Projeto projeto;

    @JsonIgnore
    @ManyToOne
	@JoinColumn(name = "usuario_id")
    private Usuario usuario;
    
}
