package com.algawoks.algafood.api.v1.openapi.model;

import java.math.BigDecimal;

import com.algawoks.algafood.api.v1.model.output.CozinhaOutput;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel(value = "RestaurantesOutput", description = "Modelo de dados para exibição resumida de restaurantes")
@Getter
@Setter
public class RestauranteOutputOpenApi {
	
	@ApiModelProperty(value = "Id do restaurante", example = "1")
	private Long id;
	
	@ApiModelProperty(value = "Nome do restaurante", example = "Delícia Brasileira")
	private String nome;
	
	@ApiModelProperty(value = "Taxa de entrega do restaurante", example = "10.00")
	private BigDecimal taxaFrete;
	
	@ApiModelProperty(value = "Modelo de dados simplificados para exibição de uma cozinha")
	private CozinhaOutput cozinhaOutput;

}
