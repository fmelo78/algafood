package com.algawoks.algafood.api.v1.model.input;

import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(description = "Modelo de dados de inclusão de um produto")
@Data
public class ProdutoInput {

	@ApiModelProperty(value = "Nome do produto", example = "Feijoada Completa", required = true)
	@NotBlank
	private String nome;
	
	@ApiModelProperty(value = "Descrição do produto", example = "Feijoada completa, com arroz, couve e farora ", required = true)
	@NotBlank
	private String descricao;
	
	@ApiModelProperty(value = "Valor do produto", example = "85.80", required = true)
	@NotNull
	@PositiveOrZero
	private BigDecimal preco;
	
	@ApiModelProperty(value = "Disponibilidade do produto no cardápio", example = "true", required = false)
	@NotNull
	private Boolean ativo;	
	
}
