package com.algawoks.algafood.api.v1.model.output;

import java.math.BigDecimal;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(description = "Modelo de dados para exibição de um item de pedido")
@Data
public class ItemPedidoOutput {
	
	@ApiModelProperty(value = "Id do produto", example = "1")
	private Long produtoId;
	
	@ApiModelProperty(value = "Nome do produto", example = "Feijoada completa")
	private String produtoNome;
	
	@ApiModelProperty(value = "Quantidade solicitada", example = "2")
	private Integer quantidade;
	
	@ApiModelProperty(value = "Preço unitário do produto", example = "100.00")
	private BigDecimal precoUnitario;
	
	@ApiModelProperty(value = "Preço total do item do pedido", example = "200.00")
	private BigDecimal precoTotal;
	
	@ApiModelProperty(value = "Observações sobre o item", example = "Sem rabo de porco")
	private String observacao;
}
