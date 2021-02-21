package com.algawoks.algafood.api.v1.model.output;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(description = "Modelo de dados para exibição de um endereço")
@Data
public class EnderecoOutput {

	@ApiModelProperty(value = "CEP do endereço", example = "05051-030")
	private String cep;
	
	@ApiModelProperty(value = "Nome do logradouro", example = "Rua das Flores")
	private String logradouro;
	
	@ApiModelProperty(value = "Número da construção", example = "104")
	private String numero;
	
	@ApiModelProperty(value = "Complemento do endereço", example = "Torre A, 15 andar")
	private String complemento;
	
	@ApiModelProperty(value = "Bairro", example = "Jardim das Belezas")
	private String bairro;
//	private String cidade;
//	private String estado;
	
	@ApiModelProperty(value = "Modelo de dados para exibição de uma cidade")
	private CidadeResumoOutput cidade;
	
}
