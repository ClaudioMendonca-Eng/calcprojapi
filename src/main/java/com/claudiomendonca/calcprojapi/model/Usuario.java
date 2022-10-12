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
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
	private String senha;
	private String email;
	private String funcao;
	private Boolean ativaadmin;


    @ManyToOne
	@JoinColumn(name = "empresasistema_id")
    private Empresasistema empresasistema;
    
}
