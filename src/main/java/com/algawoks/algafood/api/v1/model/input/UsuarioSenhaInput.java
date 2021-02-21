package com.algawoks.algafood.api.v1.model.input;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(description = "Modelo de dados para alteração de senha")
@Data
public class UsuarioSenhaInput {

	@ApiModelProperty(value = "Senha atual do usuário", example = "teste1234", required = true)
	@NotBlank
	private String senhaAtual;
	
	@ApiModelProperty(value = "Nova senha do usuário", example = "nova1234", required = true)
	@NotBlank
	private String novaSenha;
}
