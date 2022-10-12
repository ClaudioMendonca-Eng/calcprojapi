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
@Table(name = "compartilhar")
public class Compartilhar {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    
	private String email;
	private boolean enviado;

    @ManyToOne
	@JoinColumn(name = "orcamento_id")
    private Orcamento orcamento;
    
}
