package com.algawoks.algafood.api.v1.model.input;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(description = "Modelo de dados para inclusão de uma forma de pagamento")
@Data
public class FormaPagamentoInput {
	
	@ApiModelProperty(value = "Nome da forma de pagamento", example = "Dinheiro", required = true)
	@NotBlank
	private String descricao;

}
