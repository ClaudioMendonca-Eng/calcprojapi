package com.claudiomendonca.calcprojapi.model;

import javax.persistence.Embeddable;

import lombok.Data;

@Embeddable
@Data
public class DadosContatos {  

	private String cnpj;
	private String endereco;
	private String cep;
	private String email;
    private String logotipo;
	private String telefone;
	private String responsavel;
    
}
