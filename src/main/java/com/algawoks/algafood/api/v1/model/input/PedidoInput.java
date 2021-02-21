package com.algawoks.algafood.api.v1.model.input;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(description = "Modelo de dados para inclusão de um pedido")
@Data
public class PedidoInput {
	
	@ApiModelProperty(value = "Modelo de dados do restaurante", required = true)
	@Valid
	@NotNull
	private RestauranteIdInput restaurante;
	
	@ApiModelProperty(value = "Modelo de dados da forma de pagamento", required = true)
	@Valid
	@NotNull
	private FormaPagamentoIdInput formaPagamento;
	
	@ApiModelProperty(value = "Modelo de dados do endereço de entrega", required = true)
	@Valid
	@NotNull
	private EnderecoInput enderecoEntrega;
	
	@ApiModelProperty(value = "Modelo de dados dos itens do pedido", required = true)	
	@Valid
	@NotNull
	@Size (min = 1)
	private List<ItemPedidoInput> itens;
	
	

}
