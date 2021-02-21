package com.algawoks.algafood.api.v1.model.output;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(description = "Modelo de dados para exibição da Forma de Pagamento")
@Data
public class FormaPagamentoOutput {

	@ApiModelProperty(value = "Id da forma de pagamento", example = "1", required = true)
	private Long id;
	
	@ApiModelProperty(value = "Nome da forma de pagamento", example = "Dinheiro", required = true)
	private String descricao;
}
