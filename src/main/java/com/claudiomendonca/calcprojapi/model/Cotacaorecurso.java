package com.claudiomendonca.calcprojapi.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "cotacaorecurso")
public class Cotacaorecurso {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    
	private double quantidade;

    @ManyToOne
	@JoinColumn(name = "recurso_id")
    private Recurso recursoid;

    @ManyToOne
	@JoinColumn(name = "orcamento_id")
    private Orcamento orcamento;


    
}
