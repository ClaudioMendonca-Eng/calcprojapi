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
@Table(name = "recurso")
public class Recurso {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
	private String nome;
	private double custohora;
	private Integer equipamentovida;    

    @ManyToOne
	@JoinColumn(name = "empresasistema_id")
    private Empresasistema empresasistema;

    @ManyToOne
	@JoinColumn(name = "tipo_id")
    private Tipo tipo;
    
}
