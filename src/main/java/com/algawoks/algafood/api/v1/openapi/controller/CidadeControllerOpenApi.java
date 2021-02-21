package com.algawoks.algafood.api.v1.openapi.controller;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

import com.algawoks.algafood.api.exceptionhander.Problem;
import com.algawoks.algafood.api.v1.model.input.CidadeInput;
import com.algawoks.algafood.api.v1.model.output.CidadeOutput;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Cidade")
public interface CidadeControllerOpenApi {
	
	@ApiOperation("Lista todas as cidades cadastradas")
	public ResponseEntity<CollectionModel<CidadeOutput>> listar();
	
	@ApiOperation("Busca uma cidade pelo ID")
	@ApiResponses({
		@ApiResponse (code = 400, message = "ID da cidade inválido", response = Problem.class),
		@ApiResponse (code = 404, message = "Cidade não encontrada", response = Problem.class),
		@ApiResponse (code = 200, message = "Dados retornados com sucesso")
	})
	public ResponseEntity<CidadeOutput> buscar(
			@ApiParam(value = "ID de uma cidade", example = "1") Long cidadeId);
	
	@ApiOperation(value = "Cadastra uma nova cidade")
	public ResponseEntity<CidadeOutput> adicionar(
			@ApiParam(name = "corpo", value = "representação de uma nova cidade") CidadeInput cidadeIn);
	
	@ApiOperation("Altera uma cidade pelo ID")
	public ResponseEntity<CidadeOutput> alterar (
			@ApiParam(example = "1")Long cidadeId, 
			@ApiParam(name = "corpo", value = "representação de uma nova cidade") CidadeInput cidadeIn);
	
	@ApiOperation("Exclui uma cidade pelo ID")
	public ResponseEntity<?> remover (
			@ApiParam(example = "1")Long cidadeId);
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}