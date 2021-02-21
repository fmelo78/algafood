package com.algawoks.algafood.api.v1.model.output;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(description = "Modelo de dados para exibição de um restaurante")
@Data
public class RestauranteResumoOutput {
	
	@ApiModelProperty(value = "Id do restaurante", example = "1")
	private Long id;
	
	@ApiModelProperty(value = "nome do restaurante", example = "Delícia Brasileira")
	private String nome;

}
