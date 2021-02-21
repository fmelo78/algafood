package com.algawoks.algafood.api.v1.model.input;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(description = "Modelo de dados para inclusão de um usuário")
@Data
public class UsuarioInput {

	@ApiModelProperty(value = "Nome do usuário", example = "João da Silva", required = true)
	@NotBlank
	private String nome;
	
	@ApiModelProperty(value = "email do usuário", example = "joão@joão.com.br", required = true)
	@NotBlank
	@Email
	private String email;
	
	@ApiModelProperty(value = "senha do usuário", example = "teste1234", required = true)
	@NotBlank
	private String senha;
	
}
