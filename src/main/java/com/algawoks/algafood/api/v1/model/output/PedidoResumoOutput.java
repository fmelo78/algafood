package com.algawoks.algafood.api.v1.model.output;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(description = "Modelo de dados resumido para exibição de um pedido")
@Data
public class PedidoResumoOutput {

	@ApiModelProperty(value = "Código do pedido", example = "3d816cf7-cb42-4655-bfc3-286a36012efa")
	private String codigo;
	
	@ApiModelProperty(value = "Valor dos itens", example = "298.90")
	private BigDecimal subtotal;
	
	@ApiModelProperty(value = "Valor da entrega", example = "10.00")
	private BigDecimal taxaFrete;
	
	@ApiModelProperty(value = "Valor total do pedido", example = "308.90")
	private BigDecimal valorTotal;
	
	@ApiModelProperty(value = "Status do pedido", example = "CRIADO")
	private String status;
	
	@ApiModelProperty(value = "Data de criação do pedido", example = "2020-12-11T01:12:07Z")
	private OffsetDateTime dataCriacao;
	
	@ApiModelProperty(value = "Modelo de dados para exibição de um restaurante")
	private RestauranteResumoOutput restaurante; 
	
	@ApiModelProperty(value = "Modelo de dados para exibição de um cliente")
	private UsuarioOutput cliente;
	
}
