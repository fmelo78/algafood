package com.algawoks.algafood.api.v2.openapi.controller;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.algawoks.algafood.api.v2.model.input.CidadeInputV2;
import com.algawoks.algafood.api.v2.model.output.CidadeOutputV2;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(tags = "Cidade")
public interface CidadeControllerV2OpenApi {

	@ApiOperation(value = "Lista as cidades", notes = "Primeira linha da descrição detalhada \n "
					+ "Segunda Linha <br>" + "<strong> Terceira linha em negrito</strong> <br />" + "Quarta linha")
	ResponseEntity<CollectionModel<CidadeOutputV2>> listar();

	@ApiOperation(value = "Busca uma cidade pelo Id")
	ResponseEntity<CidadeOutputV2> buscar( 
			@ApiParam(value = "Id da cidade", example = "1", required = true) Long cidadeId);

	@ApiOperation(value = "Cadastra uma nova cidade")
	ResponseEntity<CidadeOutputV2> adicionar(
			@ApiParam(required = true, value = "Modelo de dados para cadastrar uma cidade", name = "CidadeInput") CidadeInputV2 cidadeIn);

	@ApiOperation(value = "Altera uma cidade existente")
	ResponseEntity<CidadeOutputV2> alterar(
			@ApiParam(example = "1", required = true, value = "Id da cidade a ser alterada")Long cidadeId, 
			@ApiParam(name = "CidadeInput", required = true, value = "Modelo de dados para alterar a cidade")CidadeInputV2 cidadeIn);

	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@ApiOperation(value = "Remove uma cidade pelo Id")
	ResponseEntity<CidadeOutputV2> remover(
			@ApiParam(example = "1", required = true, value = "Id da cidade a ser alterada")Long cidadeId);

}