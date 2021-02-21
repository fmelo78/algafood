package com.algawoks.algafood.api.v1.model.input;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(description = "Modelo de dados de uma cozinha")
@Data
public class CozinhaInput {
	
	@ApiModelProperty(value = "Nome da cozinha", required = true, example = "Chinesa")
	@NotBlank
	private String nome;

}
