package com.algawoks.algafood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.algawoks.algafood.domain.exception.PermissaoNaoEncontradaException;
import com.algawoks.algafood.domain.model.Permissao;
import com.algawoks.algafood.domain.repository.PermissaoRepository;

@Service
public class PermissaoService {
	
	@Autowired
	private PermissaoRepository permissaoRepository;

	public Permissao buscarOuFalhar (Long permissaoId) {
		Permissao permissao = permissaoRepository.findById(permissaoId).orElseThrow(() -> new PermissaoNaoEncontradaException
				(String.format("A permissão de código %d é inexistente", permissaoId)));
		return permissao;		
	}
	
}
