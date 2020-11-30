package com.algawoks.algafood.api.model.output;

import com.algawoks.algafood.api.model.view.RestauranteView;
import com.fasterxml.jackson.annotation.JsonView;

import lombok.Data;

@Data
public class CozinhaOutput {

	@JsonView (RestauranteView.Resumo.class)
	private Long id;
	
	@JsonView (RestauranteView.Resumo.class)
	private String nome;
	
}
