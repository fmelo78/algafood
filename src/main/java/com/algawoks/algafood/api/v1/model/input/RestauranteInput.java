package com.algawoks.algafood.api.v1.model.input;

import java.math.BigDecimal;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(description = "Modelo de dados para inclusão de um restaurante")
@Data
public class RestauranteInput {
		
	@ApiModelProperty(value = "Nome do restaurante", example = "Rancho Caipira", required = true)
	@NotBlank
	private String nome;
	
	@ApiModelProperty(value = "Taxa de entrega do restaurante", example = "10.00", required = true)
	@NotNull
	@PositiveOrZero
	private BigDecimal taxaFrete;
		
	@ApiModelProperty(value = "Modelo de dados para inclusão de um Id de cozinha", required = true)
	@Valid
	@NotNull
	private CozinhaIdInput cozinha;

	@ApiModelProperty(value = "Modelo de dados para inclusão de um endereço", required = true)
	@Valid
	@NotNull
	private EnderecoInput endereco;
}
