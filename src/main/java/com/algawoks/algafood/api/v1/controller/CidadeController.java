package com.algawoks.algafood.api.v1.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
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
import com.algawoks.algafood.api.v1.converter.CidadeConverter;
import com.algawoks.algafood.api.v1.model.input.CidadeInput;
import com.algawoks.algafood.api.v1.model.output.CidadeOutput;
import com.algawoks.algafood.api.v1.openapi.controller.CidadeControllerOpenApi;
import com.algawoks.algafood.domain.exception.EstadoNaoEncontradoException;
import com.algawoks.algafood.domain.exception.NegocioException;
import com.algawoks.algafood.domain.model.Cidade;
import com.algawoks.algafood.domain.repository.CidadeRepository;
import com.algawoks.algafood.domain.service.CidadeService;


@RestController
@RequestMapping(path = "/v1/cidades", produces = MediaType.APPLICATION_JSON_VALUE)
//@RequestMapping(path = "/cidades", produces = "application/vnd.algafood.v1+json")
public class CidadeController implements CidadeControllerOpenApi{
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private CidadeService cidadeService;
	
	@Autowired
	private CidadeConverter cidadeConverter;

	@Deprecated
	@GetMapping
	public ResponseEntity<CollectionModel<CidadeOutput>> listar(){
		List<Cidade> cidades = cidadeRepository.findAll();
		CollectionModel<CidadeOutput> cidadesOut = cidadeConverter.toCidadeOutList(cidades);	
		return ResponseEntity.status(HttpStatus.OK).body(cidadesOut);
	}
	
	@GetMapping("/{cidadeId}")
	public ResponseEntity<CidadeOutput> buscar(@PathVariable Long cidadeId){
		Cidade cidade = cidadeService.buscarOuFalhar(cidadeId);
		CidadeOutput cidadeOut = cidadeConverter.toCidadeOut(cidade);
		return ResponseEntity.status(HttpStatus.OK).body(cidadeOut);
	}
	
	@PostMapping()
	public ResponseEntity<CidadeOutput> adicionar(@RequestBody @Valid CidadeInput cidadeIn){
		try {
			Cidade cidade = cidadeConverter.toCidade(cidadeIn);
			Cidade cidadeTemp = cidadeService.salvar(cidade);
			CidadeOutput cidadeOut = cidadeConverter.toCidadeOut(cidadeTemp);
			
			HttpHeaders headers = ResourceUriHelper.addHeaderLocation(cidadeOut.getId());
			
			return ResponseEntity.status(HttpStatus.CREATED).headers(headers).body(cidadeOut);
		} catch (EstadoNaoEncontradoException ex) {
			throw new NegocioException(ex.getMessage(), ex);
		}
	}
	
	@PutMapping("/{cidadeId}")
	public ResponseEntity<CidadeOutput> alterar (@PathVariable Long cidadeId, @RequestBody @Valid CidadeInput cidadeIn){
		try {
			Cidade cidadeTemp1 = cidadeService.buscarOuFalhar(cidadeId);
			cidadeConverter.copyToCidade(cidadeIn, cidadeTemp1);
			Cidade cidadeTemp2 = cidadeService.salvar(cidadeTemp1);
			CidadeOutput cidadeOut = cidadeConverter.toCidadeOut(cidadeTemp2);
			return ResponseEntity.status(HttpStatus.OK).body(cidadeOut);
		} catch (EstadoNaoEncontradoException ex) {
			throw new NegocioException(ex.getMessage(), ex);
		}
	}
	
	@DeleteMapping("/{cidadeId}")
	public ResponseEntity<?> remover (@PathVariable Long cidadeId){
		cidadeService.remover(cidadeId);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

	
	
	
	
	
	
	
	

	
	
	
//	private String getUrl () {
//		return request.getRequestURI();
//	}
	
	
	
	
	
}
