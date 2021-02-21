package com.algawoks.algafood.api.v1.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.hateoas.CollectionModel;
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

import com.algawoks.algafood.api.v1.converter.UsuarioConverter;
import com.algawoks.algafood.api.v1.model.input.UsuarioInput;
import com.algawoks.algafood.api.v1.model.input.UsuarioSemSenhaInput;
import com.algawoks.algafood.api.v1.model.input.UsuarioSenhaInput;
import com.algawoks.algafood.api.v1.model.output.UsuarioOutput;
import com.algawoks.algafood.api.v1.openapi.controller.UsuarioControllerOpenApi;
import com.algawoks.algafood.domain.model.Usuario;
import com.algawoks.algafood.domain.repository.UsuarioRepository;
import com.algawoks.algafood.domain.service.UsuarioService;

@EnableAutoConfiguration
@RestController
@RequestMapping(path = "/v1/usuarios", produces = MediaType.APPLICATION_JSON_VALUE)
public class UsuarioController implements UsuarioControllerOpenApi{
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private UsuarioConverter usuarioConverter;
	
	@GetMapping
//	public ResponseEntity<List<UsuarioOutput>> listar (){
	public ResponseEntity<CollectionModel<UsuarioOutput>> listar (){
		List<Usuario> usuarios = usuarioRepository.findAll();
		CollectionModel<UsuarioOutput> usuariosOut = usuarioConverter.toUsuarioListOut(usuarios);
		return ResponseEntity.status(HttpStatus.OK).body(usuariosOut);
	}
	
	@GetMapping ("/{usuarioId}")
	public ResponseEntity<UsuarioOutput> buscar (@PathVariable Long usuarioId){
		Usuario usuario = usuarioService.buscarOuFalhar(usuarioId);
		UsuarioOutput usuarioOut = usuarioConverter.toUsuarioOut(usuario);
		return ResponseEntity.status(HttpStatus.OK).body(usuarioOut);
	}
	
	@PostMapping
	public ResponseEntity<UsuarioOutput> adicionar (@Valid @RequestBody UsuarioInput usuarioIn){
		Usuario usuario = usuarioConverter.toUsuario(usuarioIn);
		Usuario usuarioT1 = usuarioService.salvar(usuario);
		UsuarioOutput usuarioOut = usuarioConverter.toUsuarioOut(usuarioT1);
		return ResponseEntity.status(HttpStatus.CREATED).body(usuarioOut);		
	}
	
	@PutMapping ("/{usuarioId}")
	public ResponseEntity<UsuarioOutput> alterar (@PathVariable Long usuarioId, @RequestBody @Valid UsuarioSemSenhaInput usuarioIn){
		Usuario usuarioT1 = usuarioService.buscarOuFalhar(usuarioId);
		usuarioConverter.copyToUsuarioSemSenha(usuarioIn, usuarioT1);
		Usuario usuarioT2 = usuarioService.salvar(usuarioT1);
		UsuarioOutput usuarioOut = usuarioConverter.toUsuarioOut(usuarioT2);		
		return ResponseEntity.status(HttpStatus.OK).body(usuarioOut);
	}
	
	@PutMapping ("/{usuarioId}/senha")
	public ResponseEntity<?> alterar (@PathVariable Long usuarioId, @RequestBody @Valid UsuarioSenhaInput usuarioIn){
		usuarioService.alterarSenha(usuarioId, usuarioIn);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
	@DeleteMapping ("/{usuarioId}")
	public ResponseEntity<?> remover (@PathVariable Long usuarioId){
		usuarioService.buscarOuFalhar(usuarioId);
		usuarioService.apagar(usuarioId);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

	
	
	
	
	
	
	
	
}
