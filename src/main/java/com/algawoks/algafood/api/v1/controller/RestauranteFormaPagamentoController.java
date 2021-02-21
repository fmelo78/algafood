package com.algawoks.algafood.api.v1.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algawoks.algafood.api.v1.converter.FormaPagamentoConverter;
import com.algawoks.algafood.api.v1.model.output.FormaPagamentoOutput;
import com.algawoks.algafood.api.v1.openapi.controller.RestauranteFormaPagamentoControllerOpenApi;
import com.algawoks.algafood.domain.model.FormaPagamento;
import com.algawoks.algafood.domain.model.Restaurante;
import com.algawoks.algafood.domain.service.RestauranteService;

@RestController
@RequestMapping(path = "/v1/restaurantes/{restauranteId}/formas-pagamento", produces = MediaType.APPLICATION_JSON_VALUE)
public class RestauranteFormaPagamentoController implements RestauranteFormaPagamentoControllerOpenApi{
	
	@Autowired
	private RestauranteService restauranteService;
	
	@Autowired
	private FormaPagamentoConverter formaPagamentoConverter;
	
	@GetMapping
	public ResponseEntity<List<FormaPagamentoOutput>> listar (@PathVariable Long restauranteId){
		Restaurante restaurante = restauranteService.buscarOuFalhar(restauranteId);
		Set<FormaPagamento> formasPagamento = restaurante.getFormasPagamento();
		List<FormaPagamento> listaDeFormasPagamento = new ArrayList<>(formasPagamento);
		List<FormaPagamentoOutput> formasPagamentoOut = formaPagamentoConverter.toFormaPagamentoOutputList(listaDeFormasPagamento);
		return ResponseEntity.status(HttpStatus.OK).body(formasPagamentoOut);		
	}
	
	@DeleteMapping("/{formaPagamentoId}")
	public ResponseEntity<?> desassociarFormaPagamento (@PathVariable Long restauranteId, @PathVariable Long formaPagamentoId){
		restauranteService.desassociarFormaPagamento(restauranteId, formaPagamentoId);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();		
	}
	
	@PutMapping("/{formaPagamentoId}")
	public ResponseEntity<?> associarFormaPagamento (@PathVariable Long restauranteId, @PathVariable Long formaPagamentoId){
		restauranteService.associarFormaPagamento(restauranteId, formaPagamentoId);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();		
	}

}
