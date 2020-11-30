package com.algawoks.algafood.api.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algawoks.algafood.api.converter.UsuarioConverter;
import com.algawoks.algafood.api.model.output.UsuarioOutput;
import com.algawoks.algafood.domain.model.Restaurante;
import com.algawoks.algafood.domain.model.Usuario;
import com.algawoks.algafood.domain.service.RestauranteService;

@RestController
@RequestMapping("/restaurantes/{restauranteId}/responsaveis")
public class RestauranteUsuarioController {
	
	@Autowired
	private RestauranteService restauranteService;	
	
	@Autowired
	private UsuarioConverter usuarioConverter;
	
	@GetMapping
	public ResponseEntity<?> listar (@PathVariable Long restauranteId){
		Restaurante restaurante = restauranteService.buscarOuFalhar(restauranteId);
		Set<Usuario> responsaveis = restaurante.getUsuariosResponsveis();
		List<Usuario> listaResponsaveis = new ArrayList<>(responsaveis);
		List<UsuarioOutput> responsaveisOut = usuarioConverter.toUsuarioListOut(listaResponsaveis);				
		return ResponseEntity.status(HttpStatus.OK).body(responsaveisOut);
	}
	
	@PutMapping ("/{usuarioId}")
	public ResponseEntity<?> associarUsuario (@PathVariable Long restauranteId, @PathVariable Long usuarioId){
		restauranteService.associarUsuario (restauranteId, usuarioId);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();				
	}
	
	@DeleteMapping ("/{usuarioId}")
	public ResponseEntity<?> desassociarUsuario (@PathVariable Long restauranteId, @PathVariable Long usuarioId){
		restauranteService.desassociarUsuario (restauranteId, usuarioId);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();				
	}

}
