package com.algawoks.algafood.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algawoks.algafood.api.converter.CidadeConverter;
import com.algawoks.algafood.api.model.input.CidadeInput;
import com.algawoks.algafood.api.model.output.CidadeOutput;
import com.algawoks.algafood.domain.exception.EstadoNaoEncontradoException;
import com.algawoks.algafood.domain.exception.NegocioException;
import com.algawoks.algafood.domain.model.Cidade;
import com.algawoks.algafood.domain.repository.CidadeRepository;
import com.algawoks.algafood.domain.service.CidadeService;

@RestController
@RequestMapping("/cidades")
public class CidadeController {
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private CidadeService cidadeService;
	
	@Autowired
	private CidadeConverter cidadeConverter;

	
	@GetMapping
	public ResponseEntity<?> listar(){
		List<Cidade> cidades = cidadeRepository.findAll();
		List<CidadeOutput> cidadesOut = cidadeConverter.toCidadeOutList(cidades);
		return ResponseEntity.status(HttpStatus.OK).body(cidadesOut);
	}
	
	@GetMapping("/{cidadeId}")
	public ResponseEntity<?> buscar(@PathVariable Long cidadeId){
		Cidade cidade = cidadeService.buscarOuFalhar(cidadeId);
		CidadeOutput cidadeOut = cidadeConverter.toCidadeOut(cidade);
		return ResponseEntity.status(HttpStatus.OK).body(cidadeOut);
	}
	
	@PostMapping()
	public ResponseEntity<?> adicionar(@RequestBody @Valid CidadeInput cidadeIn){
		try {
			Cidade cidade = cidadeConverter.toCidade(cidadeIn);
			Cidade cidadeTemp = cidadeService.salvar(cidade);
			CidadeOutput cidadeOut = cidadeConverter.toCidadeOut(cidadeTemp);
			return ResponseEntity.status(HttpStatus.CREATED).body(cidadeOut);
		} catch (EstadoNaoEncontradoException ex) {
			throw new NegocioException(ex.getMessage(), ex);
		}
	}
	
	@PutMapping("/{cidadeId}")
	public ResponseEntity<?> alterar (@PathVariable Long cidadeId, @RequestBody @Valid CidadeInput cidadeIn){
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
	
}
