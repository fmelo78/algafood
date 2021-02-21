package com.algawoks.algafood.api.v1.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algawoks.algafood.api.v1.converter.FormaPagamentoConverter;
import com.algawoks.algafood.api.v1.model.input.FormaPagamentoInput;
import com.algawoks.algafood.api.v1.model.output.FormaPagamentoOutput;
import com.algawoks.algafood.api.v1.openapi.controller.FormaPagamentoControllerOpenApi;
import com.algawoks.algafood.domain.model.FormaPagamento;
import com.algawoks.algafood.domain.repository.FormaPagamentoRepository;
import com.algawoks.algafood.domain.service.FormaPagamentoService;

@RestController
@RequestMapping(value = "/v1/formas-pagamento", produces = MediaType.APPLICATION_JSON_VALUE)
public class FormaPagamentoController implements FormaPagamentoControllerOpenApi{
	
	@Autowired
	private FormaPagamentoRepository formaPagamentoRepository;
	
	@Autowired
	private FormaPagamentoService formaPagamentoService;
	
	@Autowired
	private FormaPagamentoConverter formaPagamentoConverter;

	@GetMapping
	public ResponseEntity<List<FormaPagamentoOutput>> listar (){
		List<FormaPagamento> formasPagamento = formaPagamentoRepository.findAll();
		List<FormaPagamentoOutput> formasPagamentoOut = new ArrayList<>();
		formasPagamentoOut = formaPagamentoConverter.toFormaPagamentoOutputList(formasPagamento);
		return ResponseEntity.status(HttpStatus.OK).cacheControl(CacheControl.maxAge(10, TimeUnit.SECONDS)).body(formasPagamentoOut);
	}
	
	@GetMapping ("/{formaPagamentoId}")
	public ResponseEntity<FormaPagamentoOutput> buscar (@PathVariable Long formaPagamentoId){
		FormaPagamento formaPagamento = formaPagamentoService.buscarOuFalhar(formaPagamentoId);
		FormaPagamentoOutput formaPagamentoOut = formaPagamentoConverter.toFormaPagamentoOutput(formaPagamento);
		return ResponseEntity.status(HttpStatus.OK)
				.cacheControl(CacheControl.maxAge(12, TimeUnit.SECONDS))
//				.cacheControl(CacheControl.maxAge(12, TimeUnit.SECONDS).cachePrivate())
//				.cacheControl(CacheControl.maxAge(12, TimeUnit.SECONDS).cachePublic())
//				.cacheControl(CacheControl.noCache())
//				.cacheControl(CacheControl.noStore())
				.body(formaPagamentoOut);
	}
	
	@PostMapping
	public ResponseEntity<FormaPagamentoOutput> criar (@RequestBody @ Valid FormaPagamentoInput formaPagamentoIn){
		FormaPagamento formaPagamento = formaPagamentoConverter.toFormaPagamento(formaPagamentoIn);
		FormaPagamento formaPagamentoT1 = formaPagamentoService.salvar(formaPagamento);
		FormaPagamentoOutput formaPagamentoOut = formaPagamentoConverter.toFormaPagamentoOutput(formaPagamentoT1);
		return ResponseEntity.status(HttpStatus.CREATED).body(formaPagamentoOut);
	}
	
	@PutMapping ("/{formaPagamentoId}")
	public ResponseEntity<FormaPagamentoOutput> alterar (@PathVariable Long formaPagamentoId, @RequestBody @ Valid FormaPagamentoInput formaPagamentoIn){
		FormaPagamento formaPagamento = formaPagamentoService.buscarOuFalhar(formaPagamentoId);
		formaPagamentoConverter.copyToFormaPagamento(formaPagamentoIn, formaPagamento);
		FormaPagamento formaPagamentoT1 = formaPagamentoService.salvar(formaPagamento);
		FormaPagamentoOutput formaPagamentoOut = formaPagamentoConverter.toFormaPagamentoOutput(formaPagamentoT1);
		return ResponseEntity.status(HttpStatus.OK).body(formaPagamentoOut);
	}
	
	@DeleteMapping ("/{formaPagamentoId}")
	public ResponseEntity<?> remover (@PathVariable Long formaPagamentoId){
		formaPagamentoService.buscarOuFalhar(formaPagamentoId);
		formaPagamentoService.remover(formaPagamentoId);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();		
	}
	
	
}
