package com.algawoks.algafood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.algawoks.algafood.domain.exception.CozinhaNaoEncontradaException;
import com.algawoks.algafood.domain.exception.EntidadeEmUsoException;
import com.algawoks.algafood.domain.model.Cozinha;
import com.algawoks.algafood.domain.repository.CozinhaRepository;

@Service
public class CozinhaService {
	
//	private static final String MSG_COZINHA_EM_USO = "A cozinha de código %d está em uso";
	
	@Autowired
	private CozinhaRepository cozinhaRepository;
	
	@Transactional
	public Cozinha salvar(Cozinha cozinha) {
		Cozinha cozinhaTemp = cozinhaRepository.save(cozinha);
		return  cozinhaTemp;
	}

	@Transactional
	public void remover (Long cozinhaId) {
		buscarOuFalhar(cozinhaId);
		try {
			cozinhaRepository.deleteById(cozinhaId);
			cozinhaRepository.flush();
		}
		catch (DataIntegrityViolationException ex) {
//			throw new EntidadeEmUsoException(String.format(MSG_COZINHA_EM_USO, cozinhaId));
			throw new EntidadeEmUsoException(Cozinha.class.getSimpleName(), cozinhaId);
		}
	}
	
	public Cozinha buscarOuFalhar(Long cozinhaId){
		Cozinha cozinha = cozinhaRepository.findById(cozinhaId).orElseThrow(() -> new CozinhaNaoEncontradaException(cozinhaId));
		return cozinha;
	}

}
