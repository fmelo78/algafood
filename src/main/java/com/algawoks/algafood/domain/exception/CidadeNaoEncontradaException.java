package com.algawoks.algafood.domain.exception;

public class CidadeNaoEncontradaException extends EntidadeNaoEncontradaException{

	private static final long serialVersionUID = 1L;
	
	public CidadeNaoEncontradaException (String mensagem) {
		super(mensagem);
	}

	public CidadeNaoEncontradaException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public CidadeNaoEncontradaException (Long cidadeId) {
		this(String.format("Cidade de código %d não encontrada", cidadeId));
	}
	
}
