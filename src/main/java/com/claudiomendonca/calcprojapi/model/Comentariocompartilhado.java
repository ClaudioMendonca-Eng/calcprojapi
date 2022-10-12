package com.claudiomendonca.calcprojapi.model;

import javax.persistence.Column;
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
@Table(name = "comentariocompartilhado")
public class Comentariocompartilhado {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "comentario")
	private String comentario;

    @JsonIgnore
    @ManyToOne
	@JoinColumn(name = "compartilhar_id")
    private Compartilhar compartilhar;
    
}
