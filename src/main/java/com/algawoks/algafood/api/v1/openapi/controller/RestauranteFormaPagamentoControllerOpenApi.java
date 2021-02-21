package com.algawoks.algafood.api.v1.openapi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ResponseStatus;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(tags = "Restaurante")
public interface RestauranteFormaPagamentoControllerOpenApi {
	
	@ApiOperation(value = "Lista as formas de pagamento cadastradas")
	public ResponseEntity<?> listar (
			@ApiParam(value = "Id do restaurante", example = "1", required = true) Long restauranteId);
	
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@ApiOperation(value = "Desassocia a forma de pagamento do restaurante")
	public ResponseEntity<?> desassociarFormaPagamento (
			@ApiParam(value = "Id do restaurante", example = "1", required = true) Long restauranteId, 
			@ApiParam(value = "Id da forma de pagamento", example = "1", required = true) Long formaPagamentoId);
	
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@ApiOperation(value = "Associa a forma de pagamento ao restaurante")
	public ResponseEntity<?> associarFormaPagamento (
			@ApiParam(value = "Id do restaurante", example = "1", required = true) Long restauranteId, 
			@ApiParam(value = "Id da forma de pagamento", example = "1", required = true) Long formaPagamentoId);

}
