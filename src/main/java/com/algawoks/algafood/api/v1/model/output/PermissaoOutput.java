package com.algawoks.algafood.api.v1.model.output;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(description = "Modelo de dados para exibição das permissões")
@Data
public class PermissaoOutput {

	@ApiModelProperty(value = "Id da permissão", example = "1")
	private Long id;
	
	@ApiModelProperty(value = "Nome da permissão", example = "Consultar_Cozinhas")
	private String nome;
	
	@ApiModelProperty(value = "Id da permissão", example = "Permite acesso read-only às cozinhas cadastradas")
	private String descricao;

}
