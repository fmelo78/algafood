package com.algawoks.algafood.api.v1.openapi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.algawoks.algafood.api.exceptionhander.Problem;
import com.algawoks.algafood.api.v1.model.input.ProdutoInput;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Produto")
public interface RestauranteProdutoControllerOpenApi {

	@ApiOperation(value = "Lista os produtos de um restaurante com um determinado status")
	public ResponseEntity<?> listar (
			@ApiParam(value = "Id do restaurante", example = "1", required = true) Long restauranteId, 
			@ApiParam(value = "Status desejado do produto", example = "ativos", required = false, allowableValues = "ativos,inativos") String status);
	
	@ApiOperation(value = "Busca um produto de um restaurante, pelos respectivos Ids")
	@ApiResponses({
		@ApiResponse(code = 400, message = "Erro no formato da requisição dos Ids", response = Problem.class),
		@ApiResponse(code = 404, message = "Produto não encontrado", response = Problem.class)
	})
	public ResponseEntity<?> buscar (
			@ApiParam(value = "Id do restaurante", example = "1", required = true) Long restauranteId, 
			@ApiParam(value = "Id do produto", example = "1", required = true) Long produtoId);
	
	@ResponseStatus(code = HttpStatus.CREATED)
	@ApiOperation(value = "Adiciona um produto a um restaurante")
	public ResponseEntity<?> adicionar (
			@ApiParam(value = "Id do restaurante", example = "1", required = true) Long restauranteId, 
			@ApiParam(value = "Modelo de dados de inclusão de um produto", required = true) ProdutoInput produtoIn);
	
	@ApiOperation(value = "Altera um produto de um restaurante")
	public ResponseEntity<?> alterar (
			@ApiParam(value = "Id do restaurante", example = "1", required = true) Long restauranteId, 
			@ApiParam(value = "Id do produto", example = "1", required = true) Long produtoId, 
			@ApiParam(value = "Modelo de dados de inclusão de um produto", required = true) ProdutoInput produtoIn);
	
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@ApiOperation(value = "Remove um produto de um restaurante")
	@ApiResponses({
		@ApiResponse(code = 400, message = "Erro no formato da requisição dos Ids", response = Problem.class),
		@ApiResponse(code = 404, message = "Produto não encontrado", response = Problem.class)
	})
	public ResponseEntity<?> remover (
			@ApiParam(value = "Id do restaurante", example = "1", required = true) Long restauranteId, 
			@ApiParam(value = "Id do produto", example = "1", required = true) Long produtoId);
}
