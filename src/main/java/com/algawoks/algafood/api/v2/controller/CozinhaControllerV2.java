package com.algawoks.algafood.api.v2.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
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

import com.algawoks.algafood.api.v2.converter.CozinhaConverterV2;
import com.algawoks.algafood.api.v2.model.input.CozinhaInputV2;
import com.algawoks.algafood.api.v2.model.output.CozinhaOutputV2;
import com.algawoks.algafood.api.v2.openapi.controller.CozinhaControllerV2OpenApi;
import com.algawoks.algafood.domain.model.Cozinha;
import com.algawoks.algafood.domain.repository.CozinhaRepository;
import com.algawoks.algafood.domain.service.CozinhaService;

@RestController
@RequestMapping(value = "/v2/cozinhas", produces = MediaType.APPLICATION_JSON_VALUE)
public class CozinhaControllerV2 implements CozinhaControllerV2OpenApi {
	
	@Autowired
	private CozinhaRepository cozinhaRepository;
	
	@Autowired
	private CozinhaService cozinhaService;
	
	@Autowired
	private CozinhaConverterV2 cozinhaConverterV2;
	
	@Autowired
	private PagedResourcesAssembler<Cozinha> pagedResourceAssembler;
	
	
//	Método utilizando paginação sem o uso de HATEOAS
//	@GetMapping
//	public ResponseEntity<Page<CozinhaOutputV2>> listar(@PageableDefault(size=2) Pageable pageable){
//		Page<Cozinha> cozinhas = cozinhaRepository.findAll(pageable);
//		O método "getContent" faz a transformação de um "Page" em um "List"
//		List<CozinhaOutputV2> cozinhasListOut = cozinhaConverter.toCozinhaOutList(cozinhas.getContent());
//		O instanciamento do "PageImpl" cria um Page com o conteúdo da lista cozinhasListOut
//		Page<CozinhaOutputV2> cozinhasPageOut = new PageImpl<CozinhaOutputV2>(cozinhasListOut, pageable, cozinhas.getTotalElements());
//		return cozinhasPageOut;
//		return ResponseEntity.status(HttpStatus.OK).body(cozinhasPageOut);
//	}
	
	@Override
	@GetMapping
	public ResponseEntity<PagedModel<CozinhaOutputV2>> listar(@PageableDefault(size=10) Pageable pageable){
		Page<Cozinha> cozinhas = cozinhaRepository.findAll(pageable);
		PagedModel<CozinhaOutputV2> cozinhasOut = pagedResourceAssembler.toModel(cozinhas, cozinhaConverterV2);
		return ResponseEntity.status(HttpStatus.OK).body(cozinhasOut);
	}
	
	@Override
	@GetMapping ("/{cozinhaId}")
	public ResponseEntity<CozinhaOutputV2> buscar(@PathVariable Long cozinhaId){
		Cozinha cozinha = cozinhaService.buscarOuFalhar(cozinhaId);
		CozinhaOutputV2 cozinhaOut = cozinhaConverterV2.toCozinhaOut(cozinha);
		return ResponseEntity.status(HttpStatus.OK).body(cozinhaOut);
	}
	
	@Override
	@GetMapping ("/por-nome")
	public ResponseEntity<List<CozinhaOutputV2>> porNome (String nome) {
		List<Cozinha> cozinhas = cozinhaRepository.findByNome(nome);
		List<CozinhaOutputV2> cozinhasOut = cozinhaConverterV2.toCozinhaOutList(cozinhas);
		return ResponseEntity.status(HttpStatus.OK).body(cozinhasOut);
	}
	
	@Override
	@PostMapping
	public ResponseEntity<CozinhaOutputV2> adicionar (@RequestBody @Valid CozinhaInputV2 cozinhaIn){
		Cozinha cozinha = cozinhaConverterV2.toCozinha(cozinhaIn);
		Cozinha cozinhaTemp = cozinhaService.salvar(cozinha);
		CozinhaOutputV2 cozinhaOut = cozinhaConverterV2.toCozinhaOut(cozinhaTemp);
		return ResponseEntity.status(HttpStatus.CREATED).body(cozinhaOut);
	}
	
	@Override
	@PutMapping ("/{cozinhaId}")
	public ResponseEntity<CozinhaOutputV2> alterar (@PathVariable Long cozinhaId, @RequestBody @Valid CozinhaInputV2 cozinhaIn){
		Cozinha cozinhaT1 = cozinhaService.buscarOuFalhar(cozinhaId);
		cozinhaConverterV2.copyToCozinha(cozinhaIn, cozinhaT1);
		Cozinha cozinhaT2 = cozinhaService.salvar(cozinhaT1);
		CozinhaOutputV2 cozinhaOut = cozinhaConverterV2.toCozinhaOut(cozinhaT2);
		return ResponseEntity.status(HttpStatus.OK).body(cozinhaOut);
	}
	
	@Override
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