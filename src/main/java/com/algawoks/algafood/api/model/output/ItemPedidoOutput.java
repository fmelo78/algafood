package com.algawoks.algafood.api.model.output;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class ItemPedidoOutput {
	
	private Long produtoId;
	private String produtoNome;
	private Integer quantidade;
	private BigDecimal precoUnitario;
	private BigDecimal precoTotal;
	private String observacao;
}
