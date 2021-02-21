package com.algawoks.algafood.api.v1.openapi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ResponseStatus;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(tags = "Usuario")
public interface UsuarioGrupoControllerOpenApi {
	
	@ApiOperation(value = "Lista os grupos de permissão associados a um usuário")
	public ResponseEntity<?> listar (
			@ApiParam(value = "Id do usuário", example = "1", required = true) Long usuarioId);
	
	@ApiOperation(value = "Associa um grupo de permissão a um usuário")
	@ResponseStatus (code = HttpStatus.NO_CONTENT)
	public ResponseEntity<?> associarGrupo (
			@ApiParam(value = "Id do usuário", example = "1", required = true) Long usuarioId, 
			@ApiParam(value = "Id do grupo", example = "1", required = true) Long grupoId);
	
	@ApiOperation(value = "Desassocia um grupo de permissão a um usuário")
	@ResponseStatus (code = HttpStatus.NO_CONTENT)
	public ResponseEntity<?> desassociarGrupo (
			@ApiParam(value = "Id do usuário", example = "1", required = true) Long usuarioId, 
			@ApiParam(value = "Id do grupo", example = "1", required = true) Long grupoId);
	
	
	
	
	

}
