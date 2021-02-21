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

import com.algawoks.algafood.api.v1.converter.GrupoConverter;
import com.algawoks.algafood.api.v1.model.output.GrupoOutput;
import com.algawoks.algafood.api.v1.openapi.controller.UsuarioGrupoControllerOpenApi;
import com.algawoks.algafood.domain.model.Grupo;
import com.algawoks.algafood.domain.model.Usuario;
import com.algawoks.algafood.domain.service.UsuarioService;

@RestController
@RequestMapping (path = "/v1/usuarios/{usuarioId}/grupos", produces = MediaType.APPLICATION_JSON_VALUE)
public class UsuarioGrupoController implements UsuarioGrupoControllerOpenApi {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private GrupoConverter grupoConverter;
	
	@GetMapping
	public ResponseEntity<List<GrupoOutput>> listar (@PathVariable Long usuarioId){
		Usuario usuario = usuarioService.buscarOuFalhar(usuarioId);
		Set<Grupo> grupos = usuario.getGrupos();
		List<Grupo> listaGrupos = new ArrayList<>(grupos);
		List<GrupoOutput> gruposOut = grupoConverter.toGrupoListOutput(listaGrupos);
		return ResponseEntity.status(HttpStatus.OK).body(gruposOut);
	}
	
	@PutMapping ("/{grupoId}")
	public ResponseEntity<?> associarGrupo (@PathVariable Long usuarioId, @PathVariable Long grupoId){
		usuarioService.associarGrupo (usuarioId, grupoId);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
	@DeleteMapping ("/{grupoId}")
	public ResponseEntity<?> desassociarGrupo (@PathVariable Long usuarioId, @PathVariable Long grupoId){
		usuarioService.desassociarGrupo (usuarioId, grupoId);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
	
	
	
	

}
