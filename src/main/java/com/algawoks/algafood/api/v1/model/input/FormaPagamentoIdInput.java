package com.algawoks.algafood.api.v1.model.input;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(description = "Modelo de dados da forma de pagamento")
@Data
public class FormaPagamentoIdInput {
	
	@ApiModelProperty(value = "Id da forma de pagamento", example = "1", required = true)
	@NotNull
	private Long id;

}
