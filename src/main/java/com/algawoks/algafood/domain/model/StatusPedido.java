package com.algawoks.algafood.domain.model;

import java.util.Arrays;
import java.util.List;

import lombok.Getter;

@Getter
public enum StatusPedido {
	
//	StatusEnumerado ("String com descrição", StatusAnterioresVálidos)
//	Todos os parâmetros devem constar no construtor da enumeração
	CRIADO ("Criado"),
	CONFIRMADO ("Confirmado", CRIADO),
	ENTREGUE ("Entregue", CONFIRMADO),
	CANCELADO ("Cancelado", CRIADO, CONFIRMADO);
	
	private String descricao;
	private List<StatusPedido> statusAnterioresPermitidos;
	
	private StatusPedido(String descricao, StatusPedido... statusAnterioresPermitidos) {
		this.descricao = descricao;
		this.statusAnterioresPermitidos = Arrays.asList(statusAnterioresPermitidos);
	}
	
	public Boolean permiteMudancaPara (StatusPedido novoStatus) {
		boolean resposta = novoStatus.statusAnterioresPermitidos.contains(this);
		return resposta;
	}
	
}
