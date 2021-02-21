package com.algawoks.algafood.api.v1.openapi.model;

import com.algawoks.algafood.api.v1.model.output.PedidoResumoOutput;

import io.swagger.annotations.ApiModel;

@ApiModel(value = "PedidosOutput", description = "Modelo de dados para paginação de pedidos")
public class PedidosOutputOpenApi extends PagedOutputOpenApi<PedidoResumoOutput>{
	
}
