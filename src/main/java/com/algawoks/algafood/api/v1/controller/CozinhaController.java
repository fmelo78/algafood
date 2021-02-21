package com.algawoks.algafood.api.v1.controller;

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
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algawoks.algafood.api.v1.converter.CozinhaConverter;
import com.algawoks.algafood.api.v1.model.input.CozinhaInput;
import com.algawoks.algafood.api.v1.model.output.CozinhaOutput;
import com.algawoks.algafood.api.v1.openapi.controller.CozinhaControllerOpenApi;
import com.algawoks.algafood.core.security.CheckSecurity;
import com.algawoks.algafood.domain.model.Cozinha;
import com.algawoks.algafood.domain.repository.CozinhaRepository;
import com.algawoks.algafood.domain.service.CozinhaService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value = "/v1/cozinhas", produces = MediaType.APPLICATION_JSON_VALUE)
public class CozinhaController implements CozinhaControllerOpenApi{
	
	@Autowired
	private CozinhaRepository cozinhaRepository;
	
	@Autowired
	private CozinhaService cozinhaService;
	
	@Autowired
	private CozinhaConverter cozinhaConverter;
	
	@Autowired
	private PagedResourcesAssembler<Cozinha> pagedResourceAssembler;
	
	
//	Método utilizando paginação sem o uso de HATEOAS
//	@GetMapping
//	public ResponseEntity<Page<CozinhaOutput>> listar(@PageableDefault(size=2) Pageable pageable){
//		Page<Cozinha> cozinhas = cozinhaRepository.findAll(pageable);
//		O método "getContent" faz a transformação de um "Page" em um "List"
//		List<CozinhaOutput> cozinhasListOut = cozinhaConverter.toCozinhaOutList(cozinhas.getContent());
//		O instanciamento do "PageImpl" cria um Page com o conteúdo da lista cozinhasListOut
//		Page<CozinhaOutput> cozinhasPageOut = new PageImpl<CozinhaOutput>(cozinhasListOut, pageable, cozinhas.getTotalElements());
//		return cozinhasPageOut;
//		return ResponseEntity.status(HttpStatus.OK).body(cozinhasPageOut);
//	}
	
//	@SuppressWarnings("unused")
	@Override
	@GetMapping
	@CheckSecurity.Cozinhas.PodeAcessar
	public ResponseEntity<PagedModel<CozinhaOutput>> listar(@PageableDefault(size=10) Pageable pageable){
		log.info("Listando as cozinhas com o limite de {} registros por página", pageable.getPageSize());
		Page<Cozinha> cozinhas = cozinhaRepository.findAll(pageable);
		PagedModel<CozinhaOutput> cozinhasOut = pagedResourceAssembler.toModel(cozinhas, cozinhaConverter);
		return ResponseEntity.status(HttpStatus.OK).body(cozinhasOut);
	}
	
	@GetMapping ("/{cozinhaId}")
	@CheckSecurity.Cozinhas.PodeAcessar
	public ResponseEntity<CozinhaOutput> buscar(@PathVariable Long cozinhaId){
		Cozinha cozinha = cozinhaService.buscarOuFalhar(cozinhaId);
		CozinhaOutput cozinhaOut = cozinhaConverter.toCozinhaOut(cozinha);
		return ResponseEntity.status(HttpStatus.OK).body(cozinhaOut);
	}
	
	@GetMapping ("/por-nome")
	@CheckSecurity.Cozinhas.PodeAcessar
	public ResponseEntity<List<CozinhaOutput>> porNome (String nome) {
		List<Cozinha> cozinhas = cozinhaRepository.findByNome(nome);
		List<CozinhaOutput> cozinhasOut = cozinhaConverter.toCozinhaOutList(cozinhas);
		return ResponseEntity.status(HttpStatus.OK).body(cozinhasOut);
	}
	
	@PostMapping
	@CheckSecurity.Cozinhas.PodeEditar
	public ResponseEntity<CozinhaOutput> adicionar (@RequestBody @Valid CozinhaInput cozinhaIn){
		System.out.println(SecurityContextHolder.getContext().getAuthentication().getAuthorities());
		Cozinha cozinha = cozinhaConverter.toCozinha(cozinhaIn);
		Cozinha cozinhaTemp = cozinhaService.salvar(cozinha);
		CozinhaOutput cozinhaOut = cozinhaConverter.toCozinhaOut(cozinhaTemp);
		return ResponseEntity.status(HttpStatus.CREATED).body(cozinhaOut);
	}
	
	@PutMapping ("/{cozinhaId}")
	@CheckSecurity.Cozinhas.PodeEditar
	public ResponseEntity<CozinhaOutput> alterar (@PathVariable Long cozinhaId, @RequestBody @Valid CozinhaInput cozinhaIn){
		Cozinha cozinhaT1 = cozinhaService.buscarOuFalhar(cozinhaId);
		cozinhaConverter.copyToCozinha(cozinhaIn, cozinhaT1);
		Cozinha cozinhaT2 = cozinhaService.salvar(cozinhaT1);
		CozinhaOutput cozinhaOut = cozinhaConverter.toCozinhaOut(cozinhaT2);
		return ResponseEntity.status(HttpStatus.OK).body(cozinhaOut);
	}
	
	@DeleteMapping ("/{cozinhaId}")
	@CheckSecurity.Cozinhas.PodeEditar
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