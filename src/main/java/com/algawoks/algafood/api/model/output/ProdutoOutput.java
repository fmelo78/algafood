package com.algawoks.algafood.api.model.output;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class ProdutoOutput {
	
	private Long id;
	private String nome;
	private String descricao;
	private BigDecimal preco;
	private Boolean ativo;	

}
