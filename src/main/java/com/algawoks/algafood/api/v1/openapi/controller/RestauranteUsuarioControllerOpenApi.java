package com.algawoks.algafood.api.v1.openapi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ResponseStatus;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(tags = "Restaurante")
public interface RestauranteUsuarioControllerOpenApi {
	
	@ApiOperation(value = "Lista os usuários responsáveis po um restaurante, com base no Id do restaurante")
	public ResponseEntity<?> listar (
			@ApiParam(value = "Id do restaurante", example = "1", required = true) Long restauranteId);
	
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@ApiOperation(value = "Associa um usuário ao restaurante, com base no Id do restaurante")
	public ResponseEntity<?> associarUsuario (
			@ApiParam(value = "Id do restaurante", example = "1", required = true) Long restauranteId, 
			@ApiParam(value = "Id do usuário", example = "1", required = true) Long usuarioId);
	
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@ApiOperation(value = "Desassocia um usuário ao restaurante, com base no Id do restaurante")
	public ResponseEntity<?> desassociarUsuario (
			@ApiParam(value = "Id do restaurante", example = "1", required = true) Long restauranteId, 
			@ApiParam(value = "Id do usuário", example = "1", required = true) Long usuarioId);

}
