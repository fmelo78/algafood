package com.algawoks.algafood.api.model.output;

import lombok.Data;

@Data
public class CidadeOutput {
	
	private Long id;
	private String nome;
	private EstadoOutput estado;

}
