package com.algawoks.algafood.domain.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.algawoks.algafood.domain.exception.GrupoNaoEncontradoException;
import com.algawoks.algafood.domain.model.Grupo;
import com.algawoks.algafood.domain.model.Permissao;
import com.algawoks.algafood.domain.repository.GrupoRepository;

@Service
public class GrupoService {
	
	@Autowired
	private GrupoRepository grupoRepository;
	
	@Autowired
	private PermissaoService permissaoService;
	
	
	public Grupo buscarOuFalhar (Long grupoId) {
		Grupo grupo = grupoRepository.findById(grupoId).orElseThrow(() -> new GrupoNaoEncontradoException(grupoId));
		return grupo;
	}

	@Transactional
	public Grupo salvar(Grupo grupo) {
		Grupo grupoT1 = grupoRepository.save(grupo);
		return grupoT1;
	}

	@Transactional
	public void remover(Long grupoId) {
		grupoRepository.deleteById(grupoId);		
	}

	@Transactional
	public void desassociarPermissao(Long grupoId, Long permissaoId) {
		Grupo grupo = buscarOuFalhar(grupoId);
		Permissao permissao = permissaoService.buscarOuFalhar(permissaoId);
//		if (!grupo.getPermissoes().contains(permissao)) {
//			throw new NegocioException(String.format
//					("O grupo de código %d não possui uma permissão de código %d", grupoId, permissaoId));
//		}
		Set<Permissao> permissoes = grupo.getPermissoes();
		permissoes.remove(permissao);
		grupoRepository.save(grupo);
	}
	
	@Transactional
	public void associarPermissao(Long grupoId, Long permissaoId) {
		Grupo grupo = buscarOuFalhar(grupoId);
		Permissao permissao = permissaoService.buscarOuFalhar(permissaoId);
		Set<Permissao> permissoes = grupo.getPermissoes();
		permissoes.add(permissao);
		grupoRepository.save(grupo);
	}
	
}
