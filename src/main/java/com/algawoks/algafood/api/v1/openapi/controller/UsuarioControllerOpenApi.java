package com.algawoks.algafood.api.v1.openapi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.algawoks.algafood.api.v1.model.input.UsuarioInput;
import com.algawoks.algafood.api.v1.model.input.UsuarioSemSenhaInput;
import com.algawoks.algafood.api.v1.model.input.UsuarioSenhaInput;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(tags = "Usuario")
public interface UsuarioControllerOpenApi {
	
	@ApiOperation(value = "lista os usuários cadastrados no sistema")
	public ResponseEntity<?> listar ();
	
	@ApiOperation(value = "Busca um usuários pelo Id")
	public ResponseEntity<?> buscar (
			@ApiParam(value = "Id do usuário", example = "1", required = true) Long usuarioId);
	
	@ApiOperation(value = "Adiciona um usuário")
	@ResponseStatus(code = HttpStatus.CREATED)
	public ResponseEntity<?> adicionar (
			@ApiParam(value = "Modelo de dados para inclusão de um usuário", required = true) UsuarioInput usuarioIn);
	
	@ApiOperation(value = "Altera um usuário pelo Id")
	public ResponseEntity<?> alterar (
			@ApiParam(value = "Id do usuário", example = "1", required = true) Long usuarioId, 
			@ApiParam(value = "Modelo de dados para inclusão de um usuário sem senha", required = true) UsuarioSemSenhaInput usuarioIn);
	
	@ApiOperation(value = "Altera a senha de um usuário pelo Id")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public ResponseEntity<?> alterar (
			@ApiParam(value = "Id do usuário", example = "1", required = true) Long usuarioId, 
			@ApiParam(value = "Modelo de dados para troca de senha", required = true) UsuarioSenhaInput usuarioIn);
	
	@ApiOperation(value = "Remove um usuário pelo Id")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public ResponseEntity<?> remover (
			@ApiParam(value = "Id do usuário", example = "1", required = true) Long usuarioId);

	
	
	
	
	
	
	
	
}
