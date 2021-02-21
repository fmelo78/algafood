package com.algawoks.algafood.api.v1.model.input;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(description = "Modelo de dados para especificação de um estado")
@Data
public class EstadoInput {
	
	@ApiModelProperty(value = "Nome do estado", example = "São Paulo", required = true)
	@NotBlank
	private String nome;

}
