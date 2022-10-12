package com.claudiomendonca.calcprojapi.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "orcamento")
public class Orcamento {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String versao;

    
	@Enumerated(EnumType.STRING)
    @Column(name = "status_orcamento")
	private Statusorcamento statusorcamento;

    private BigDecimal comissao;
    
    @Column(name = "margem_lucro")
    private BigDecimal margemlucro;

    private BigDecimal fiscal;

    @Column(name = "data_vencimento")
    private LocalDate datavencimento;

    private String investimento;

    private String prazo;

    @ManyToOne
	@JoinColumn(name = "projeto_id")
    private Projeto projeto;

}
