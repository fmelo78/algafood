package com.algawoks.algafood.api.v1.openapi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartFile;

import com.algawoks.algafood.api.v1.model.input.FotoProdutoInput;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(tags = "Produto")
public interface FotoProdutoControllerOpenApi {
	
	@ApiOperation(value = "Atualiza a foto de um produto")
	public ResponseEntity<?> atualizarFoto(
			@ApiParam(value = "Id do restaurante", example = "1", required = true) Long restauranteId, 
			@ApiParam(value = "Id do produto", example = "1", required = true) Long produtoId,
			FotoProdutoInput fotoProdutoInput,
			@ApiParam(value = "Arquivo da foto", required = true) MultipartFile arquivo);
	
	@ApiOperation(value = "Busca as informações de uma foto de um produto", produces = "application/json, image/jpeg, image/png")
	public ResponseEntity<?> buscarMetadados (
			@ApiParam(value = "Id do restaurante", example = "1", required = true) Long restauranteId, 
			@ApiParam(value = "Id do produto", example = "1", required = true) Long produtoId);
	
	@ApiOperation(value = "Busca a foto de um produto pelos Ids do restaurante e do produto", hidden = true)
	public ResponseEntity<?> buscarFoto (
			@ApiParam(value = "Id do restaurante", example = "1", required = true) Long restauranteId, 
			@ApiParam(value = "Id do produto", example = "1", required = true) Long produtoId,
			@RequestHeader(name = "accept") String acceptHeader) throws HttpMediaTypeNotAcceptableException;
	
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@ApiOperation(value = "Remove a foto de um produto pelos Ids do restaurante e do produto")
	public ResponseEntity<?> removerFoto (
			@ApiParam(value = "Id do restaurante", example = "1", required = true) Long restauranteId, 
			@ApiParam(value = "Id do produto", example = "1", required = true) Long produtoId);
	
}















