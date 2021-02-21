package com.algawoks.algafood.api.v1.openapi.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.algawoks.algafood.api.v1.model.input.PedidoInput;
import com.algawoks.algafood.api.v1.model.output.PedidoOutput;
import com.algawoks.algafood.api.v1.model.output.PedidoResumoOutput;
import com.algawoks.algafood.api.v1.openapi.model.PedidosOutputOpenApi;
import com.algawoks.algafood.domain.filter.PedidoFilter;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(tags = "Pedido")
public interface PedidoControllerOpenApi {
	

	@ApiOperation(value = "Pesquisa os pedidos, com base nos filtros", response = PedidosOutputOpenApi.class)
	@ApiImplicitParams({
		@ApiImplicitParam(value = "Nomes das propriedades para filtrar a resposta, separadas por vírgula",
				name = "campos", example = "nome,restauranteId", paramType = "query", type = "string")
	})
	public ResponseEntity<Page<PedidoResumoOutput>> pesquisar(
			@ApiParam(value = "Filtro de pesquisa") PedidoFilter filtro, 
			@ApiParam(value = "XXXXXXXXXXXXXXXXXXX") Pageable pageable);

	@ApiOperation(value = "Busca um pedido pelo código")
	@ApiImplicitParams({
		@ApiImplicitParam(value = "Nomes das propriedades para filtrar a resposta, separadas por vírgula",
				name = "campos", example = "nome,restauranteId", paramType = "query", type = "string")
	})
	public ResponseEntity<PedidoOutput> buscar (
			@ApiParam(value = "Código do pedido", example = "3d816cf7-cb42-4655-bfc3-286a36012efa", required = true) String codigoPedido);
	
	@ResponseStatus(code = HttpStatus.CREATED)
	@ApiOperation(value = "Adiciona um pedido")
	public ResponseEntity<PedidoOutput> adicionar (
			@ApiParam(value = "Modelo de dados para inclusão de um pedido", required = true) PedidoInput pedidoIn);
	
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@ApiOperation(value = "Altera o status do pedido para confirmado")
	public ResponseEntity<?> confirmar (
			@ApiParam(value = "Código do pedido", example = "3d816cf7-cb42-4655-bfc3-286a36012efa", required = true) String codigoPedido);
	
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@ApiOperation(value = "Altera o status do pedido para cancelado")
	public ResponseEntity<?> cancelar (
			@ApiParam(value = "Código do pedido", example = "3d816cf7-cb42-4655-bfc3-286a36012efa", required = true) String codigoPedido);
	
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@ApiOperation(value = "Altera o status do pedido para entregue")
	public ResponseEntity<?> entregar (
			@ApiParam(value = "Código do pedido", example = "3d816cf7-cb42-4655-bfc3-286a36012efa", required = true) String codigoPedido);
	
}











