package com.algawoks.algafood.api.v1.model.input;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class EstadoIdInput {
	
	@NotNull
	private Long id;

}
