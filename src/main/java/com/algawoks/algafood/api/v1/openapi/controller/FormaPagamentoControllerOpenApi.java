package com.algawoks.algafood.api.v1.openapi.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.algawoks.algafood.api.exceptionhander.Problem;
import com.algawoks.algafood.api.v1.model.input.FormaPagamentoInput;
import com.algawoks.algafood.api.v1.model.output.FormaPagamentoOutput;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Forma de Pagamento")
public interface FormaPagamentoControllerOpenApi {
	
	@ApiOperation(value = "Lista as formas de pagamento existentes")
	public ResponseEntity<List<FormaPagamentoOutput>> listar ();
	
	@ApiOperation(value = "Busca uma forma de pagamento pelo Id")
	@ApiResponses({
		@ApiResponse(code = 400, message = "Id inválido da forma de pagamento", response = Problem.class),
		@ApiResponse(code = 404, message = "Forma de Pagamento Inexistente", response = Problem.class)
	})
	public ResponseEntity<FormaPagamentoOutput> buscar (
			@ApiParam(value = "Id da forma de pagamento", example = "1", required = true) Long formaPagamentoId);
	
	@ResponseStatus(code = HttpStatus.CREATED)
	@ApiOperation(value = "Cria uma forma de pagamento")
	@ApiResponses({
		@ApiResponse(code = 400, message = "Os parâmetros informados são inválidos", response = Problem.class),
		@ApiResponse(code = 404, message = "Forma de Pagamento Inexistente", response = Problem.class)
	})
	public ResponseEntity<FormaPagamentoOutput> criar (
			@ApiParam(value = "Representção de entrada de uma forma de pagamento", required = true) FormaPagamentoInput formaPagamentoIn);
	
	@ApiOperation(value = "Altera uma forma de pagamento pelo Id")
	@ApiResponses({
		@ApiResponse(code = 400, message = "Os parâmetros informados são inválidos", response = Problem.class),
		@ApiResponse(code = 404, message = "Forma de Pagamento Inexistente", response = Problem.class)
	})
	public ResponseEntity<FormaPagamentoOutput> alterar (
			@ApiParam(value = "Id da forma de pagamento", example = "1", required = true) Long formaPagamentoId, 
			@ApiParam(value = "Representção de entrada de uma forma de pagamento", required = true) FormaPagamentoInput formaPagamentoIn);
	
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@ApiOperation(value = "Remove uma forma de pagamento pelo Id")
	@ApiResponses({
		@ApiResponse(code = 400, message = "Os parâmetros informados são inválidos", response = Problem.class),
		@ApiResponse(code = 404, message = "Forma de Pagamento Inexistente", response = Problem.class)
	})
	public ResponseEntity<?> remover (
			@ApiParam(value = "Id da forma de pagamento", example = "1", required = true) Long formaPagamentoId);
	
}
