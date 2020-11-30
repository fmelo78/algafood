package com.algawoks.algafood.infrastructure.exception;

import com.algawoks.algafood.domain.exception.EntidadeNaoEncontradaException;

public class FotoNaoEncontradaException extends EntidadeNaoEncontradaException{

	private static final long serialVersionUID = 1L;

	public FotoNaoEncontradaException(String message, Throwable cause) {
		super(message, cause);
	}

	public FotoNaoEncontradaException(String mensagem) {
		super(mensagem);
	}
	
}
