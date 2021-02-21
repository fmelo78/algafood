package com.algawoks.algafood.api.v1.openapi.controller;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.algawoks.algafood.api.exceptionhander.Problem;
import com.algawoks.algafood.api.v1.model.input.CozinhaInput;
import com.algawoks.algafood.api.v1.model.output.CozinhaOutput;
import com.algawoks.algafood.api.v1.openapi.model.CozinhasOutputOpenApi;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Cozinha")
public interface CozinhaControllerOpenApi {
	
//	@ApiOperation(value = "Lista as cozinhas cadastradas", response = CozinhasOutputOpenApi.class)
//	public ResponseEntity<PagedModel<CozinhaOutput>> listar(Pageable pageable);
	@ApiOperation(value = "Lista as cozinhas cadastradas", response = CozinhasOutputOpenApi.class)
	public ResponseEntity<PagedModel<CozinhaOutput>> listar(Pageable pageable);
	
	@ApiOperation(value = "Busca uma cozinha pelo Id")
	@ApiResponses({
		@ApiResponse(code = 400, message = "Id da cozinha inválido", response = Problem.class),
		@ApiResponse(code = 404, message = "Cozinha não encontrada", response = Problem.class)
	})
	public ResponseEntity<CozinhaOutput> buscar(@ApiParam(value = "Id da cozinha", example = "1", required = true) Long cozinhaId);
	
	@ApiOperation(value = "Busca uma cozinha pelo nome")
	public ResponseEntity<List<CozinhaOutput>> porNome (
			@RequestParam @ApiParam(value = "Nome da cozinha", example = "São Paulo") String nome);
	
	@ResponseStatus(code = HttpStatus.CREATED)
	@ApiOperation(value = "Adiciona uma cozinha")
	public ResponseEntity<CozinhaOutput> adicionar (
			@ApiParam(value = "Modelo de dados de uma cozinha", required = true) CozinhaInput cozinhaIn);
	
	@ApiOperation(value = "Altera uma cozinha pelo Id")
	@ApiResponses({
		@ApiResponse(code = 400, message = "Id da cozinha inválido", response = Problem.class),
		@ApiResponse(code = 404, message = "Cozinha não encontrada", response = Problem.class)
	})
	public ResponseEntity<CozinhaOutput> alterar (
			@ApiParam(value = "Id da cozinha", example = "1", required = true) Long cozinhaId, 
			@ApiParam(value = "Modelo de dados de uma cozinha", required = true) CozinhaInput cozinhaIn);
	
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@ApiOperation(value = "Remove uma cozinha pelo Id")
	@ApiResponses({
		@ApiResponse(code = 400, message = "Id da cozinha inválido", response = Problem.class),
		@ApiResponse(code = 404, message = "Cozinha não encontrada", response = Problem.class)
	})
	public ResponseEntity<?> remover (@ApiParam(value = "Id da cozinha", example = "1", required = true) Long cozinhaId);
}	
