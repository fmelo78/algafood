package com.algawoks.algafood.api.model.output;

import lombok.Data;

@Data
public class EnderecoOutput {

	private String cep;
	private String logradouro;
	private String numero;
	private String complemento;
	private String bairro;
//	private String cidade;
//	private String estado;
	private CidadeResumoOutput cidade;
	
}
