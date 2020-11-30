package com.algawoks.algafood.api.converter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.algawoks.algafood.api.model.input.UsuarioInput;
import com.algawoks.algafood.api.model.input.UsuarioSemSenhaInput;
import com.algawoks.algafood.api.model.output.UsuarioOutput;
import com.algawoks.algafood.domain.model.Usuario;

@Component
public class UsuarioConverter {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public UsuarioOutput toUsuarioOut (Usuario usuario) {
		UsuarioOutput usuarioOut = modelMapper.map(usuario, UsuarioOutput.class);
		return usuarioOut;		
	}
	
	public List<UsuarioOutput> toUsuarioListOut (List<Usuario> usuarios) {
		List<UsuarioOutput> usuariosOut = new ArrayList<>();
		usuariosOut = usuarios.stream().map(usuario -> toUsuarioOut(usuario)).collect(Collectors.toList());
		return usuariosOut;		
	}
	
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
	

}
