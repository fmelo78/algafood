package com.algawoks.algafood.api.v1.openapi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.algawoks.algafood.api.exceptionhander.Problem;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Grupo")
public interface GrupoPermissaoControllerOpenApi {

	@ApiOperation(value = "Lista as permissões atribuídao ao grupo, pelo Id")
	public ResponseEntity<?> listar (
			@ApiParam(value = "Id do grupo", example = "1", required = true) Long grupoId);
	
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@ApiOperation(value = "Desassocia uma permissão ao grupo")
	@ApiResponses({
		@ApiResponse(code = 400, message = "Formato inválido do parâmetro da requisição", response = Problem.class),
		@ApiResponse(code = 404, message = "Ids de grupo e/ou permissão inexistentes", response = Problem.class)
	})
	public ResponseEntity<?> desassociarPermissao (
			@ApiParam(value = "Id do grupo", example = "1", required = true) Long grupoId, 
			@ApiParam(value = "Id da permissão", example = "1", required = true) Long permissaoId);
	
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@ApiOperation(value = "Associa uma permissão ao grupo")
	@ApiResponses({
		@ApiResponse(code = 400, message = "Formato inválido do parâmetro da requisição", response = Problem.class),
		@ApiResponse(code = 404, message = "Ids de grupo e/ou permissão inexistentes", response = Problem.class)
	})
	public ResponseEntity<?> associarPermissao (
			@ApiParam(value = "Id do grupo", example = "1", required = true) Long grupoId, 
			@ApiParam(value = "Id da permissão", example = "1", required = true) Long permissaoId);


}
