package com.algawoks.algafood.api.v1.openapi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.algawoks.algafood.api.v1.model.input.EstadoInput;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(tags = "Estado")
public interface EstadoControllerOpenApi {
	
	@ApiOperation(value = "Lista os estados cadastrados")
	public ResponseEntity<?> listar();
	
	@ApiOperation(value = "Busca um estado pelo Id")
	public ResponseEntity<?> buscar(
			@ApiParam(value = "Id do estado", example = "1", required = true) Long estadoId);
	
	@ResponseStatus (code = HttpStatus.CREATED)
	@ApiOperation(value = "diciona um estado")
	public ResponseEntity<?> adicionar(
			@ApiParam(value = "Modelo de dados para inclusão de um estado", required = true) EstadoInput estadoIn);
	
	@ApiOperation(value = "Atualiza um estado pelo Id")
	public ResponseEntity<?> atualizar(
			@ApiParam(value = "Id do estado", example = "1", required = true) Long estadoId, 
			@ApiParam(value = "Modelo de dados para inclusão de um estado", required = true) EstadoInput estadoIn);
	
	@ResponseStatus (code = HttpStatus.NO_CONTENT)
	@ApiOperation(value = "Remove um estado pelo Id")
	public ResponseEntity<?> remover (
			@ApiParam(value = "Id do estado", example = "1", required = true)Long estadoId);

}
