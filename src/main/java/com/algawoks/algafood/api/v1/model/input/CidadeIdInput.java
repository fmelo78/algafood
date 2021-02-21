package com.algawoks.algafood.api.v1.model.input;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(description = "Id da cidade")
@Data
public class CidadeIdInput {

	@ApiModelProperty(value = "Id da cidade", example = "1", required = true)
	@NotNull
	private Long id;
}
