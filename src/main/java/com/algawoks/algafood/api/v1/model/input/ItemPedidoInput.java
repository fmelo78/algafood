package com.algawoks.algafood.api.v1.model.input;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(description = "Modelo de dados para criação de um item de pedido")
@Data
public class ItemPedidoInput {
	
	@ApiModelProperty(value = "Id do produto", example = "1", required = true)
	@NotNull
	private Long produtoId;
	
	@ApiModelProperty(value = "Quantidade do produto", example = "2", required = true)
	@NotNull
	@Positive
	private Integer quantidade;
	
	@ApiModelProperty(value = "Observações sobre o produto", example = "Sem pé de porco", required = false)
	private String observacao;
}
