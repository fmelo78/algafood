package com.algawoks.algafood.api.v1.openapi.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.algawoks.algafood.api.v1.model.input.RestauranteInput;
import com.algawoks.algafood.api.v1.model.output.RestauranteOutput;
import com.algawoks.algafood.api.v1.openapi.model.RestauranteOutputOpenApi;
import com.algawoks.algafood.domain.model.Restaurante;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(tags = "Restaurante")
public interface RestauranteControllerOpenApi {
	
	@ApiOperation(value = "Lista os restaurantes cadastrados", response = RestauranteOutputOpenApi.class)
	@ApiImplicitParams({
		@ApiImplicitParam(value = "Nome da projeção de pedidos", allowableValues = "apenas-nome", 
				name = "projecao", paramType = "query", type = "string")
	})
	public ResponseEntity<List<RestauranteOutput>> listar();
	
	@ApiOperation(value = "Lista os restaurantes cadastrados", hidden = true)
	public ResponseEntity<?> listarApenasNomes();
	
	@ApiOperation(value = "Busca um restaurante pelo Id")
	public ResponseEntity<RestauranteOutput> buscar(
			@ApiParam(value = "Id do restaurante", example = "1", required = true) Long restauranteId);

	@ResponseStatus(code = HttpStatus.CREATED)
	@ApiOperation(value = "Adiciona um restaurante")
	public ResponseEntity<RestauranteOutput> adicionar( 
			@ApiParam(value = "Modelo de dados para inclusão de um restaurante") RestauranteInput restauranteIn);

	@ApiOperation(value = "Altera um restaurante pelo Id")
	public ResponseEntity<RestauranteOutput> alterar( 
			@ApiParam(value = "Id do restaurante", example = "1", required = true) Long restauranteId,
			@ApiParam(value = "Modelo de dados para inclusão de um restaurante", required = true) RestauranteInput restauranteIn);

	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@ApiOperation(value = "Remove um restaurante pelo Id")
	public ResponseEntity<?> remover(
			@ApiParam(value = "Id do restaurante", example = "1", required = true) Long restauranteId);

	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@ApiOperation(value = "Ativa um restaurante pelo Id")
	public ResponseEntity<?> ativar (
			@ApiParam(value = "Id do restaurante", example = "1", required = true) Long restauranteId);

	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@ApiOperation(value = "Inativa um restaurante pelo Id")
	public ResponseEntity<?> inativar (
			@ApiParam(value = "Id do restaurante", example = "1", required = true) Long restauranteId);

	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@ApiOperation(value = "Inicia a operação de um restaurante pelo Id")
	public ResponseEntity<?> abrir (
			@ApiParam(value = "Id do restaurante", example = "1", required = true) Long restauranteId);

	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@ApiOperation(value = "Encerra a operação de um restaurante pelo Id")
	public ResponseEntity<?> fechar (
			@ApiParam(value = "Id do restaurante", example = "1", required = true) Long restauranteId);

	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@ApiOperation(value = "Ativa restaurantes em lote")
	public ResponseEntity<?> ativarVarios (
			@ApiParam(value = "Lista de Id de restaurantes", required = true) List<Long> restaurantesId);

	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@ApiOperation(value = "Desativa erstaurantes em lote")
	public ResponseEntity<?> desativarVarios (
			@ApiParam(value = "Lista de Id de restaurantes", required = true) List<Long> restaurantesId);
	
	
//	Trecho de código para uso com classe RestauranteSpecs
	@ApiOperation(value = "Lista restaurantes com frete grátis pelo nome")
	public ResponseEntity<List<Restaurante>> listarComFreteGratis(
			@ApiParam(value = "Texto a ser pesquisado", example = "brasi", required = false) String nome);

	@ApiOperation(value = "Lista restaurantes pela especificação da taxa de entrega")
	public ResponseEntity<List<Restaurante>> listarTaxaFrete(
			@ApiParam(value = "Taxa de frete inicial da pesquisa", example = "10.00", required = false) BigDecimal taxa1, 
			@ApiParam(value = "Taxa de frete final da pesquisa", example = "15.00", required = false) BigDecimal taxa2);

	@ApiOperation(value = "Verifica a existência de um restaurante pelo nome")
	public ResponseEntity<Boolean> verificarNome(
			@ApiParam(value = "Texto a ser pesquisado", example = "brasi", required = true) String nome);
	
	@ApiOperation(value = "Verifica a quantidade de restaurantes de uma cozinha")
	public ResponseEntity<Integer> contarCozinha(
			@ApiParam(value = "Id da cozinha", example = "1", required = true) Long cozinhaId);
	
	@ApiOperation(value = "Busca restaurantes pela especificação do nome e da cozinha")
	public ResponseEntity<List<Restaurante>> buscarNomeCozinhaId(
			@ApiParam(value = "Texto a ser pesquisado", example = "brasi", required = true) String nome, 
			@ApiParam(value = "Id da cozinha", example = "1", required = false) Long cozinhaId);

	@ApiOperation(value = "Busca restaurantes pela especificação do nome e da taxa de entrega")
	public ResponseEntity<List<Restaurante>> buscarNomeFrete(
			@ApiParam(value = "Texto a ser pesquisado", example = "brasi",  required = false) String nome, 
			@ApiParam(value = "Taxa de frete inicial da pesquisa", example = "10.00", type = "Query", required = false) BigDecimal taxaInicial, 
			@ApiParam(value = "Taxa de frete final da pesquisa", example = "15.00", type = "query", required = false) BigDecimal taxaFinal);

}
