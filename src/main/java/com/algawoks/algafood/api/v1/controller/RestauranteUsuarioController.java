package com.algawoks.algafood.api.v1.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algawoks.algafood.api.v1.converter.UsuarioConverter;
import com.algawoks.algafood.api.v1.model.output.UsuarioOutput;
import com.algawoks.algafood.api.v1.openapi.controller.RestauranteUsuarioControllerOpenApi;
import com.algawoks.algafood.domain.model.Restaurante;
import com.algawoks.algafood.domain.model.Usuario;
import com.algawoks.algafood.domain.service.RestauranteService;

@RestController
@RequestMapping(path = "/v1/restaurantes/{restauranteId}/responsaveis", produces = MediaType.APPLICATION_JSON_VALUE)
public class RestauranteUsuarioController implements RestauranteUsuarioControllerOpenApi{
	
	@Autowired
	private RestauranteService restauranteService;	
	
	@Autowired
	private UsuarioConverter usuarioConverter;
	
	@GetMapping
	public ResponseEntity<CollectionModel<UsuarioOutput>> listar (@PathVariable Long restauranteId){
		Restaurante restaurante = restauranteService.buscarOuFalhar(restauranteId);
		Set<Usuario> responsaveis = restaurante.getUsuariosResponsveis();
		List<Usuario> listaResponsaveis = new ArrayList<>(responsaveis);
		CollectionModel<UsuarioOutput> responsaveisOut = usuarioConverter.toUsuarioListOut(listaResponsaveis);				
		responsaveisOut.removeLinks();
		responsaveisOut.add(WebMvcLinkBuilder.linkTo
				(WebMvcLinkBuilder.methodOn(RestauranteUsuarioController.class).listar(restauranteId))
				.withRel(IanaLinkRelations.SELF));
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
