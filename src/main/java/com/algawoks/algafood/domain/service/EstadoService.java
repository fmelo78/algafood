package com.algawoks.algafood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.algawoks.algafood.domain.exception.EntidadeEmUsoException;
import com.algawoks.algafood.domain.exception.EstadoNaoEncontradoException;
import com.algawoks.algafood.domain.model.Estado;
import com.algawoks.algafood.domain.repository.EstadoRepository;

@Service
public class EstadoService {
	
	@Autowired
	private EstadoRepository estadoRepository;

	@Transactional
	public Estado salvar(Estado estado) {
		Estado estadoTemp = estadoRepository.save(estado);
		return estadoTemp;
	}
	
	@Transactional
	public void remover(Long estadoId) {
		try {
			buscarOuFalhar(estadoId);
			estadoRepository.deleteById(estadoId);		
			estadoRepository.flush();
		}
		catch (DataIntegrityViolationException ex) {
//			throw new EntidadeEmUsoException(String.format("O estado de código %d está em uso", estadoId));
			throw new EntidadeEmUsoException(Estado.class.getSimpleName(), estadoId);
		}
	}
	
	public Estado buscarOuFalhar (Long estadoId) {
		Estado estado = estadoRepository.findById(estadoId).orElseThrow(() -> new EstadoNaoEncontradoException(estadoId));
		return estado;
	}
	
}
