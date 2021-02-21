package com.algawoks.algafood.api.v2.model.input;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel(description = "Modelo de dados de uma cozinha", value = "CozinhaInput")
@Getter
@Setter
public class CozinhaInputV2 {
	
	@ApiModelProperty(value = "Nome da cozinha", required = true, example = "Chinesa")
	@NotBlank
	private String nomeCozinha;

}
