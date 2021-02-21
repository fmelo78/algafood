package com.algawoks.algafood.api.v1.model.input;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(description = "Modelo de dados para especificação de um endereço")
@Data
public class EnderecoInput {

	@ApiModelProperty(value = "CEP do endereço", example = "05051-030", required = true)
	@NotBlank
	private String cep;
	
	@ApiModelProperty(value = "logradouro do endereço", example = "Rua das Flores", required = true)
	@NotBlank
	private String logradouro;
	
	@ApiModelProperty(value = "Número do endereço", example = "104", required = true)
	@NotBlank
	private String numero;
	
	@ApiModelProperty(value = "complemento do endereço", example = "Torre A, 15 andar", required = false)
	private String complemento;
	
	@ApiModelProperty(value = "Bairro do endereço", example = "Vila das Belezas", required = true)
	@NotBlank
	private String bairro;
	
	@ApiModelProperty(value = "Modelo de dados da cidade", required = true)
	@Valid
	@NotNull
	private CidadeIdInput cidade;
	
}
