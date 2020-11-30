package com.algawoks.algafood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.algawoks.algafood.domain.exception.EntidadeEmUsoException;
import com.algawoks.algafood.domain.exception.FormaPagamentoNaoEncontradaException;
import com.algawoks.algafood.domain.model.FormaPagamento;
import com.algawoks.algafood.domain.repository.FormaPagamentoRepository;

@Service
public class FormaPagamentoService {
	
	@Autowired
	private FormaPagamentoRepository formaPagamentoRepository;

	public FormaPagamento buscarOuFalhar (Long formaPagamentoId) {
		FormaPagamento formaPagamento = formaPagamentoRepository.findById(formaPagamentoId)
				.orElseThrow(() -> new FormaPagamentoNaoEncontradaException(formaPagamentoId));
		return formaPagamento;
	}

	@Transactional
	public FormaPagamento salvar(FormaPagamento formaPagamento) {
		FormaPagamento formaPagamentoT1 = formaPagamentoRepository.save(formaPagamento);
		return formaPagamentoT1;
	}

	@Transactional
	public void remover(Long formaPagamentoId) {
		try {
			formaPagamentoRepository.deleteById(formaPagamentoId);		
			formaPagamentoRepository.flush();
		}
		catch (DataIntegrityViolationException ex) {
			throw new EntidadeEmUsoException(FormaPagamento.class.getSimpleName(), formaPagamentoId);
		}
	}

}
