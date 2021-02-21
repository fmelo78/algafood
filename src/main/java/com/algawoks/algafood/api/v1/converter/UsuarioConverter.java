package com.algawoks.algafood.api.v1.converter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import com.algawoks.algafood.api.v1.controller.UsuarioController;
import com.algawoks.algafood.api.v1.controller.UsuarioGrupoController;
import com.algawoks.algafood.api.v1.model.input.UsuarioInput;
import com.algawoks.algafood.api.v1.model.input.UsuarioSemSenhaInput;
import com.algawoks.algafood.api.v1.model.output.UsuarioOutput;
import com.algawoks.algafood.domain.model.Usuario;

@Component
//public class UsuarioConverter {
public class UsuarioConverter extends RepresentationModelAssemblerSupport<Usuario, UsuarioOutput>{
	
	@Autowired
	private ModelMapper modelMapper;

	
	public UsuarioConverter() {
		super(UsuarioController.class, UsuarioOutput.class);
	}
	
	public UsuarioOutput toUsuarioOut (Usuario usuario) {
		UsuarioOutput usuarioOut = modelMapper.map(usuario, UsuarioOutput.class);
		usuarioOut.add(WebMvcLinkBuilder.linkTo
				(WebMvcLinkBuilder.methodOn(UsuarioController.class).buscar(usuarioOut.getId()))
				.withRel(IanaLinkRelations.SELF));
		usuarioOut.add(WebMvcLinkBuilder.linkTo
				(WebMvcLinkBuilder.methodOn(UsuarioController.class).listar())
				.withRel(IanaLinkRelations.COLLECTION));
		usuarioOut.add(WebMvcLinkBuilder.linkTo
				(WebMvcLinkBuilder.methodOn(UsuarioGrupoController.class).listar(usuarioOut.getId()))
				.withRel("grupos-usuario"));
		return usuarioOut;		
	}
	
	public CollectionModel<UsuarioOutput> toUsuarioListOut (List<Usuario> usuarios) {
		List<UsuarioOutput> usuariosOut = new ArrayList<>();
		usuariosOut = usuarios.stream().map(usuario -> toUsuarioOut(usuario)).collect(Collectors.toList());
		CollectionModel<UsuarioOutput> collectionOut = CollectionModel.of(usuariosOut);
		collectionOut.add(WebMvcLinkBuilder.linkTo
				(WebMvcLinkBuilder.methodOn(UsuarioController.class).listar().getBody())
				.withRel(IanaLinkRelations.SELF));
		return collectionOut;		
	}
//	public List<UsuarioOutput> toUsuarioListOut (List<Usuario> usuarios) {
//		List<UsuarioOutput> usuariosOut = new ArrayList<>();
//		usuariosOut = usuarios.stream().map(usuario -> toUsuarioOut(usuario)).collect(Collectors.toList());
//		return usuariosOut;		
//	}
	
	public Usuario toUsuario (UsuarioInput usuarioIn) {
		Usuario usuario = modelMapper.map(usuarioIn, Usuario.class);
		return usuario;		
	}
	
	public void copyToUsuario (UsuarioInput usuarioIn, Usuario usuario) {
		modelMapper.map(usuarioIn, usuario);
	}

	public void copyToUsuarioSemSenha (UsuarioSemSenhaInput usuarioIn, Usuario usuario) {
		modelMapper.map(usuarioIn, usuario);		
	}

	@Override
	public UsuarioOutput toModel(Usuario entity) {
		return null;
	}
	

}
