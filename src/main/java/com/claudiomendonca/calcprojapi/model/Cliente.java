package com.claudiomendonca.calcprojapi.model;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Entity
@Data
@Table(name = "cliente")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotNull
    private String nome;

    @Embedded
	private DadosContatos dadosContatos;
    
	@NotNull
	private Boolean ativo;

    @ManyToOne
	@JoinColumn(name = "empresasistema_id")
    private Empresasistema empresasistema;
    
}
