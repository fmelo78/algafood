package com.algawoks.algafood.domain.exception;

public class PermissaoNaoEncontradaException extends EntidadeNaoEncontradaException{

	private static final long serialVersionUID = 1L;

	public PermissaoNaoEncontradaException(String message, Throwable cause) {
		super(message, cause);
	}

	public PermissaoNaoEncontradaException(String mensagem) {
		super(mensagem);
	}
	
}
