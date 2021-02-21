package com.algawoks.algafood.api.v2.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpHeaders;
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

import com.algawoks.algafood.api.helper.ResourceUriHelper;
import com.algawoks.algafood.api.v2.converter.CidadeConverterV2;
import com.algawoks.algafood.api.v2.model.input.CidadeInputV2;
import com.algawoks.algafood.api.v2.model.output.CidadeOutputV2;
import com.algawoks.algafood.api.v2.openapi.controller.CidadeControllerV2OpenApi;
import com.algawoks.algafood.domain.exception.EstadoNaoEncontradoException;
import com.algawoks.algafood.domain.exception.NegocioException;
import com.algawoks.algafood.domain.model.Cidade;
import com.algawoks.algafood.domain.repository.CidadeRepository;
import com.algawoks.algafood.domain.service.CidadeService;


@RestController
@RequestMapping(path = "/v2/cidades", produces = MediaType.APPLICATION_JSON_VALUE)
public class CidadeControllerV2 implements CidadeControllerV2OpenApi {
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private CidadeService cidadeService;
	
	@Autowired
	private CidadeConverterV2 cidadeConverterV2;

	@Override
	@GetMapping
	public ResponseEntity<CollectionModel<CidadeOutputV2>> listar(){
		List<Cidade> cidades = cidadeRepository.findAll();
		List<CidadeOutputV2> cidadesOut = cidadeConverterV2.toCidadeOutList(cidades);	
		CollectionModel<CidadeOutputV2> resposta = CollectionModel.of(cidadesOut);
		resposta.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CidadeControllerV2.class).listar()).withSelfRel());
		return ResponseEntity.status(HttpStatus.OK).body(resposta);
	}
	
	@Override
	@GetMapping("/{cidadeId}")
	public ResponseEntity<CidadeOutputV2> buscar(@PathVariable Long cidadeId){
		Cidade cidade = cidadeService.buscarOuFalhar(cidadeId);
		CidadeOutputV2 cidadeOut = cidadeConverterV2.toCidadeOut(cidade);
		return ResponseEntity.status(HttpStatus.OK).body(cidadeOut);
	}
	
	@Override
	@PostMapping()
	public ResponseEntity<CidadeOutputV2> adicionar(@RequestBody @Valid CidadeInputV2 cidadeIn){
		try {
			Cidade cidade = cidadeConverterV2.toCidade(cidadeIn);
			Cidade cidadeTemp = cidadeService.salvar(cidade);
			CidadeOutputV2 cidadeOut = cidadeConverterV2.toCidadeOut(cidadeTemp);
			
			HttpHeaders headers = ResourceUriHelper.addHeaderLocation(cidadeOut.getIdCidade());
			
			return ResponseEntity.status(HttpStatus.CREATED).headers(headers).body(cidadeOut);
		} catch (EstadoNaoEncontradoException ex) {
			throw new NegocioException(ex.getMessage(), ex);
		}
	}
	
	@Override
	@PutMapping("/{cidadeId}")
	public ResponseEntity<CidadeOutputV2> alterar (@PathVariable Long cidadeId, @RequestBody @Valid CidadeInputV2 cidadeIn){
		try {
			Cidade cidadeTemp1 = cidadeService.buscarOuFalhar(cidadeId);
			cidadeConverterV2.copyToCidade(cidadeIn, cidadeTemp1);
			Cidade cidadeTemp2 = cidadeService.salvar(cidadeTemp1);
			CidadeOutputV2 cidadeOut = cidadeConverterV2.toCidadeOut(cidadeTemp2);
			return ResponseEntity.status(HttpStatus.OK).body(cidadeOut);
		} catch (EstadoNaoEncontradoException ex) {
			throw new NegocioException(ex.getMessage(), ex);
		}
	}
	
	@Override
	@DeleteMapping("/{cidadeId}")
	public ResponseEntity<CidadeOutputV2> remover (@PathVariable Long cidadeId){
		cidadeService.remover(cidadeId);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

	
	
	
	
	
	
	
	

	
	
	
//	private String getUrl () {
//		return request.getRequestURI();
//	}
	
	
	
	
	
}
