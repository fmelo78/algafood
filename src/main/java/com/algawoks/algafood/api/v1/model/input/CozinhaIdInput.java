package com.algawoks.algafood.api.v1.model.input;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(description = "Modelo de dados para especificação de uma cozinha")
@Data
public class CozinhaIdInput {
	
	@ApiModelProperty(value = "Id da cozinha", example = "1", required = true)
	@NotNull
	private Long id;

}
