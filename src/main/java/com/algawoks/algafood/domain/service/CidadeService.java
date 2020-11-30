package com.algawoks.algafood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.algawoks.algafood.domain.exception.CidadeNaoEncontradaException;
import com.algawoks.algafood.domain.exception.EntidadeEmUsoException;
import com.algawoks.algafood.domain.model.Cidade;
import com.algawoks.algafood.domain.model.Estado;
import com.algawoks.algafood.domain.repository.CidadeRepository;

@Service
public class CidadeService {
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private EstadoService estadoService;
	
	@Transactional
	public Cidade salvar (Cidade cidade) {
		Long estadoId = cidade.getEstado().getId();
		Estado estado = estadoService.buscarOuFalhar(estadoId);
		cidade.setEstado(estado);
		Cidade cidadeTemp1 = cidadeRepository.save(cidade);
		return cidadeTemp1;
	}
		
	@Transactional
	public void remover (Long cidadeId) {
		buscarOuFalhar(cidadeId);
		try {
			cidadeRepository.deleteById(cidadeId);
			cidadeRepository.flush();
		}
		catch (DataIntegrityViolationException ex) {
//			throw new EntidadeEmUsoException(String.format("Cidade de código %d em uso", cidadeId));
			throw new EntidadeEmUsoException(Cidade.class.getSimpleName(), cidadeId);
		}
	}
		
	public Cidade buscarOuFalhar (Long cidadeId) {
		Cidade cidade = cidadeRepository.findById(cidadeId).orElseThrow(() -> 
			new CidadeNaoEncontradaException(cidadeId));
		return cidade;
	}

}
