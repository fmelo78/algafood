package com.algawoks.algafood.domain.exception;

public class ProdutoNaoEncontradoException extends EntidadeNaoEncontradaException{

	private static final long serialVersionUID = 1L;

	public ProdutoNaoEncontradoException(String message, Throwable cause) {
		super(message, cause);
	}

	public ProdutoNaoEncontradoException(String mensagem) {
		super(mensagem);
	}

}
