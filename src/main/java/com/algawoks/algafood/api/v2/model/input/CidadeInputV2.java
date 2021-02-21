package com.algawoks.algafood.api.v2.model.input;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel(value = "CidadeInput", description = "Modelo de dados para inclusão / alteração de uma cidade")
@Getter
@Setter
public class CidadeInputV2 {
	
	@ApiModelProperty(example = "São Caetano", required = true, value = "Nome da cidade")
	@NotBlank
	private String nome;
	
	@ApiModelProperty(example = "2", required = true, value = "Id do estado")
	@Valid
	@NotNull
	private Long idEstado;

}
