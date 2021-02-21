package com.algawoks.algafood.api.v2.openapi.controller;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

import com.algawoks.algafood.api.v2.model.input.CozinhaInputV2;
import com.algawoks.algafood.api.v2.model.output.CozinhaOutputV2;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(tags = "Cozinha")
public interface CozinhaControllerV2OpenApi {

	@ApiOperation(value = "Lista as cozinhas cadastradas")
	ResponseEntity<PagedModel<CozinhaOutputV2>> listar(Pageable pageable);

	@ApiOperation(value = "Busca uma cozinha pelo Id")
	ResponseEntity<CozinhaOutputV2> buscar(
			@ApiParam(value = "Id da cozinha", example = "1", required = true) Long cozinhaId);

	@ApiOperation(value = "Busca uma cozinha pelo nome")
	ResponseEntity<List<CozinhaOutputV2>> porNome(
			@RequestParam
			@ApiParam(value = "Cozinha a ser pesquisada", example = "Brasileira", required = false) String nome);

	@ApiOperation(value = "Cadastra uma nova cozinha")
	ResponseEntity<CozinhaOutputV2> adicionar(
			@ApiParam(value = "Modelo de dados para cadastrar uma nova cozinha", required = true) CozinhaInputV2 cozinhaIn);

	@ApiOperation(value = "Altera uma cozinha existente")
	ResponseEntity<CozinhaOutputV2> alterar(
			@ApiParam(value = "Id da cozinha", example = "1", required = true) Long cozinhaId, 
			@ApiParam(value = "Modelo de dados para cadastrar uma nova cozinha", required = true) CozinhaInputV2 cozinhaIn);

	@ApiOperation(value = "Remove uma cozinha pelo Id")
	ResponseEntity<?> remover(
			@ApiParam(value = "Id da cozinha", example = "1", required = true) Long cozinhaId);

}