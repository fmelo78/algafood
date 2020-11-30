package com.algawoks.algafood.api.model.input;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class FormaPagamentoIdInput {
	
	@NotNull
	private Long id;

}
