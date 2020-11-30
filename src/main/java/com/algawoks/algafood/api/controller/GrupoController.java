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

import com.algawoks.algafood.api.converter.GrupoConverter;
import com.algawoks.algafood.api.model.input.GrupoInput;
import com.algawoks.algafood.api.model.output.GrupoOutput;
import com.algawoks.algafood.domain.model.Grupo;
import com.algawoks.algafood.domain.repository.GrupoRepository;
import com.algawoks.algafood.domain.service.GrupoService;

@RestController
@RequestMapping("/grupos")
public class GrupoController {
	
	@Autowired
	private GrupoRepository grupoRepository;
	
	@Autowired
	private GrupoService grupoService;
	
	@Autowired
	private GrupoConverter grupoConverter;
	
	@GetMapping
	public ResponseEntity<?> listar () {
		List<Grupo> grupos = grupoRepository.findAll();
		List<GrupoOutput> gruposOut = grupoConverter.toGrupoListOutput(grupos);
		return ResponseEntity.status(HttpStatus.OK).body(gruposOut);
	}
	
	@GetMapping ("/{grupoId}")
	public ResponseEntity<?> buscar (@PathVariable Long grupoId){
		Grupo grupo = grupoService.buscarOuFalhar(grupoId);
		GrupoOutput grupoOut = grupoConverter.toGrupoOutput(grupo);
		return ResponseEntity.status(HttpStatus.OK).body(grupoOut);
	}
	
	@PostMapping
	public ResponseEntity<?> adicionar (@RequestBody @Valid GrupoInput grupoIn){
		Grupo grupo = grupoConverter.toGrupo(grupoIn);
		Grupo grupoT1 = grupoService.salvar(grupo);
		GrupoOutput grupoOut = grupoConverter.toGrupoOutput(grupoT1);
		return ResponseEntity.status(HttpStatus.CREATED).body(grupoOut);
	}
	
	@PutMapping ("/{grupoId}")
	public ResponseEntity<?> alterar (@PathVariable Long grupoId, @RequestBody @Valid GrupoInput grupoIn){
		Grupo grupoT1 = grupoService.buscarOuFalhar(grupoId);
		grupoConverter.copyToGrupo(grupoIn, grupoT1);
		Grupo grupoT2 = grupoService.salvar(grupoT1);
		GrupoOutput grupoOut = grupoConverter.toGrupoOutput(grupoT2);		
		return ResponseEntity.status(HttpStatus.OK).body(grupoOut);
	}
	
	@DeleteMapping ("/{grupoId}")
	public ResponseEntity<?> remover (@PathVariable Long grupoId){
		grupoService.buscarOuFalhar(grupoId);
		grupoService.remover(grupoId);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

}
