package com.algawoks.algafood.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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

import com.algawoks.algafood.api.converter.CozinhaConverter;
import com.algawoks.algafood.api.model.input.CozinhaInput;
import com.algawoks.algafood.api.model.output.CozinhaOutput;
import com.algawoks.algafood.domain.model.Cozinha;
import com.algawoks.algafood.domain.repository.CozinhaRepository;
import com.algawoks.algafood.domain.service.CozinhaService;

@RestController
@RequestMapping(value = "/cozinhas")
public class CozinhaController {
	
	@Autowired
	private CozinhaRepository cozinhaRepository;
	
	@Autowired
	private CozinhaService cozinhaService;
	
	@Autowired
	private CozinhaConverter cozinhaConverter;
	
	
	@GetMapping
	public ResponseEntity<?> listar(@PageableDefault(size=2) Pageable pageable){
		Page<Cozinha> cozinhas = cozinhaRepository.findAll(pageable);
//		O método "getContent" faz a transformação de um "Page" em um "List"
		List<CozinhaOutput> cozinhasListOut = cozinhaConverter.toCozinhaOutList(cozinhas.getContent());
//		O instanciamento do "PageImpl" cria um Page com o conteúdo da lista cozinhasListOut
		Page<CozinhaOutput> cozinhasPageOut = new PageImpl<CozinhaOutput>(cozinhasListOut, pageable, cozinhas.getTotalElements());
		return ResponseEntity.status(HttpStatus.OK).body(cozinhasPageOut);
	}
	
	@GetMapping ("/{cozinhaId}")
	public ResponseEntity<?> buscar(@PathVariable Long cozinhaId){
		Cozinha cozinha = cozinhaService.buscarOuFalhar(cozinhaId);
		CozinhaOutput cozinhaOut = cozinhaConverter.toCozinhaOut(cozinha);
		return ResponseEntity.status(HttpStatus.OK).body(cozinhaOut);
	}
	
	@GetMapping ("/por-nome")
	public ResponseEntity<?> porNome (String nome) {
		List<Cozinha> cozinhas = cozinhaRepository.findByNome(nome);
		List<CozinhaOutput> cozinhasOut = cozinhaConverter.toCozinhaOutList(cozinhas);
		return ResponseEntity.status(HttpStatus.OK).body(cozinhasOut);
	}
	
	@PostMapping
	public ResponseEntity<?> adicionar (@RequestBody @Valid CozinhaInput cozinhaIn){
		Cozinha cozinha = cozinhaConverter.toCozinha(cozinhaIn);
		Cozinha cozinhaTemp = cozinhaService.salvar(cozinha);
		CozinhaOutput cozinhaOut = cozinhaConverter.toCozinhaOut(cozinhaTemp);
		return ResponseEntity.status(HttpStatus.CREATED).body(cozinhaOut);
	}
	
	@PutMapping ("/{cozinhaId}")
	public ResponseEntity<?> alterar (@PathVariable Long cozinhaId, @RequestBody @Valid CozinhaInput cozinhaIn){
		Cozinha cozinhaT1 = cozinhaService.buscarOuFalhar(cozinhaId);
		cozinhaConverter.copyToCozinha(cozinhaIn, cozinhaT1);
		Cozinha cozinhaT2 = cozinhaService.salvar(cozinhaT1);
		CozinhaOutput cozinhaOut = cozinhaConverter.toCozinhaOut(cozinhaT2);
		return ResponseEntity.status(HttpStatus.OK).body(cozinhaOut);
	}
	
	@DeleteMapping ("/{cozinhaId}")
	public ResponseEntity<?> remover (@PathVariable Long cozinhaId){
		cozinhaService.remover(cozinhaId);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
}	

	

//	@DeleteMapping ("/{cozinhaId}")
//	public ResponseEntity<?> remover (@PathVariable Long cozinhaId){
//		try {
//			cozinhaService.remover(cozinhaId);
//			return ResponseEntity.status(HttpStatus.OK).build();
//		}
//		catch (EntidadeNaoEncontradaException ex) {
//			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//		}
//		catch (EntidadeEmUsoException ex) {
//			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
//		}
//	}
//	
//	@GetMapping("/asdfasd")
//	public ResponseEntity<List<Cozinha>> list(){
//		List<Cozinha> cozinhas = cozinhaRepository.findAll();
//		HttpHeaders headers = new HttpHeaders();
//		headers.add(HttpHeaders.LOCATION, "http://localhost:8080/cozinhas/1");
//		return ResponseEntity.status(HttpStatus.FOUND).headers(headers).body(cozinhas);
//	}
//}	