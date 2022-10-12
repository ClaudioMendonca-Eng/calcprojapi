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
@Table(name = "clientecomentario")
public class Clientecomentario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
	private String comentario;
    
    @JsonIgnore
    @ManyToOne
	@JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @JsonIgnore
    @ManyToOne
	@JoinColumn(name = "orcamento_id")
    private Orcamento orcamento;
    
}
