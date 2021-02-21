package com.algawoks.algafood.api.v1.openapi.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.algawoks.algafood.api.exceptionhander.Problem;
import com.algawoks.algafood.api.v1.model.input.GrupoInput;
import com.algawoks.algafood.api.v1.model.output.GrupoOutput;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Grupo")
public interface GrupoControllerOpenApi {
	
	@ApiOperation("Lista todos os grupos existentes")
	public ResponseEntity<List<GrupoOutput>> listar ();
	
	@ApiOperation("Busca um grupo pelo Id")
	@ApiResponses({
		@ApiResponse (code = 400, message = "Formato inválido do Id", response = Problem.class),
		@ApiResponse (code = 404, message = "Grupo não encontrado", response = Problem.class)
	})
	public ResponseEntity<GrupoOutput> buscar (
			@ApiParam(value = "Id do grupo", example = "1") Long grupoId);
	
//	Anotação necessária para a correta identificação do código de retorno no Swagger
	@ResponseStatus(code = HttpStatus.CREATED)
	@ApiOperation("Adiciona um grupo")
	@ApiResponses({
		@ApiResponse (code = 201, message = "Grupo adicionado com sucesso", response = GrupoOutput.class)
	})
	public ResponseEntity<GrupoOutput> adicionar (
			@ApiParam(value = "Objeto do tipo grupo") GrupoInput grupoIn);
	
	@ApiOperation("Altera um grupo pelo Id")
	@ApiResponses({
		@ApiResponse (code = 200, message = "Grupo alterado com sucesso", response = GrupoOutput.class),
		@ApiResponse (code = 400, message = "Formato inválido do Id", response = Problem.class),
		@ApiResponse (code = 404, message = "Grupo não encontrado", response = Problem.class)
	})
	public ResponseEntity<GrupoOutput> alterar (
			@ApiParam(value = "Id do grupo", example = "1") Long grupoId, 
			@ApiParam(value = "Objeto do tipo grupo") GrupoInput grupoIn);
	
//	Anotação necessária para a correta identificação do código de retorno no Swagger
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@ApiOperation("Remove um grupo pelo Id")
	@ApiResponses({
		@ApiResponse (code = 204, message = "Grupo removido com sucesso"),
		@ApiResponse (code = 400, message = "Erro na requisição", response = Problem.class),
		@ApiResponse (code = 404, message = "Grupo não encontrado", response = Problem.class)
	})
	public ResponseEntity<?> remover (
			@ApiParam(value = "Id do grupo", example = "1") Long grupoId);

}
