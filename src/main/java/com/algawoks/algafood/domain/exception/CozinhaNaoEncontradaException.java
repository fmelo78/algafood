package com.algawoks.algafood.domain.exception;

public class CozinhaNaoEncontradaException extends EntidadeNaoEncontradaException{

	private static final long serialVersionUID = 1L;
	
	public CozinhaNaoEncontradaException (String mensagem) {
		super(mensagem);
	}

	public CozinhaNaoEncontradaException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public CozinhaNaoEncontradaException (Long cozinhaId) {
		this(String.format("Cozinha de código %d não encontrada", cozinhaId));
	}
	
}
