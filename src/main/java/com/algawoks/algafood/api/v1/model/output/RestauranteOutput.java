package com.algawoks.algafood.api.v1.model.output;

import java.math.BigDecimal;

import com.algawoks.algafood.api.v1.model.view.RestauranteView;
import com.fasterxml.jackson.annotation.JsonView;

import lombok.Data;

@Data
public class RestauranteOutput {
	
	@JsonView ({ RestauranteView.Resumo.class, RestauranteView.ApenasNome.class })
	private Long id;
	
	@JsonView ({ RestauranteView.Resumo.class, RestauranteView.ApenasNome.class })
	private String nome;
	
	@JsonView (RestauranteView.Resumo.class)
	private BigDecimal taxaFrete;
	
	@JsonView (RestauranteView.Resumo.class)
	private CozinhaOutput cozinha;
	
	private EnderecoOutput endereco;
	private Boolean ativo;
	private Boolean aberto;

}
