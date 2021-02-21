package com.algawoks.algafood.api.v1.model.input;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "GrupoInput", description = "Modelo de dados para entrada de um novo grupo")
@Data
public class GrupoInput {
	
	@ApiModelProperty(required = true, example = "Atendentes", value = "Nome do grupo")
	@NotBlank
	private String nome;

}
