package com.algawoks.algafood.api.v1.model.input;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class CidadeInput {
	
	@ApiModelProperty(example = "São Caetano", required = true)
	@NotBlank
	private String nome;
	
	@Valid
	@NotNull
	private EstadoIdInput estado;

}
