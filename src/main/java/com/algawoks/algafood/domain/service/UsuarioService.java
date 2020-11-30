package com.algawoks.algafood.domain.service;

import java.util.Optional;
import java.util.Set;

import javax.persistence.EntityManager;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.algawoks.algafood.api.model.input.UsuarioSenhaInput;
import com.algawoks.algafood.domain.exception.NegocioException;
import com.algawoks.algafood.domain.exception.SenhasDiferentesException;
import com.algawoks.algafood.domain.exception.UsuarioNaoEncontradoException;
import com.algawoks.algafood.domain.model.Grupo;
import com.algawoks.algafood.domain.model.Usuario;
import com.algawoks.algafood.domain.repository.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private GrupoService grupoService;
	
	@Autowired
	private EntityManager entityManager;

	public Usuario buscarOuFalhar(Long usuarioId) {
		Usuario usuario = usuarioRepository.findById(usuarioId).orElseThrow(() -> new UsuarioNaoEncontradoException(usuarioId));
		return usuario;
	}

	@Transactional
	public Usuario salvar(Usuario usuario) {
		entityManager.detach(usuario);
		Optional<Usuario> usuarioExistente = usuarioRepository.findByEmail(usuario.getEmail());
		if (usuarioExistente.isPresent() && !usuarioExistente.get().equals(usuario)) {
			throw new NegocioException(String.format("Já existe um usuário cadastrado com o email %s", usuario.getEmail()));
		}
		Usuario usuarioT1 = usuarioRepository.save(usuario);
		return usuarioT1;
	}

	@Transactional
	public void apagar(Long usuarioId) {
		usuarioRepository.deleteById(usuarioId);		
		usuarioRepository.flush();
	}

	@Transactional
	public void alterarSenha(Long usuarioId, UsuarioSenhaInput usuarioSenha) {
		Usuario usuarioAtual = buscarOuFalhar(usuarioId);
		String senhaAtual = usuarioAtual.getSenha();
		String senhaAtualInformada = usuarioSenha.getSenhaAtual();
		String novaSenha = usuarioSenha.getNovaSenha();
		if (StringUtils.equals(senhaAtual, senhaAtualInformada)) {
			usuarioAtual.setSenha(novaSenha);
			salvar(usuarioAtual);
		}
		else {
			throw new SenhasDiferentesException("A senha atual informada não coincide com a senha do usuário");
		}
	}

	@Transactional
	public void associarGrupo(Long usuarioId, Long grupoId) {
		Usuario usuario = buscarOuFalhar(usuarioId);
		Grupo grupo = grupoService.buscarOuFalhar(grupoId);
		Set<Grupo> grupos = usuario.getGrupos();
		grupos.add(grupo);
		usuarioRepository.save(usuario);
	}

	public void desassociarGrupo(Long usuarioId, Long grupoId) {
		Usuario usuario = buscarOuFalhar(usuarioId);
		Grupo grupo = grupoService.buscarOuFalhar(grupoId);
		Set<Grupo> grupos = usuario.getGrupos();
		grupos.remove(grupo);
		usuarioRepository.save(usuario);
	}


	
}
