package com.algawoks.algafood.api.model.output;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

import lombok.Data;

@Data
public class PedidoOutput {

	private String codigo;
	private BigDecimal subtotal;
	private BigDecimal taxaFrete;
	private BigDecimal valorTotal;
	private String status;
	private OffsetDateTime dataCriacao;
	private OffsetDateTime dataConfirmacao;
	private OffsetDateTime dataEntrega;
	private OffsetDateTime dataCancelamento;
	private RestauranteResumoOutput restaurante; 
	private UsuarioOutput cliente;
	private FormaPagamentoOutput formaPagamento;
	private EnderecoOutput enderecoEntrega;
	private List<ItemPedidoOutput> itens;
	
}
