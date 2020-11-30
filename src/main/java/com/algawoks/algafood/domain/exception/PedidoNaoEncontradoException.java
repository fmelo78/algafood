package com.algawoks.algafood.domain.exception;

public class PedidoNaoEncontradoException extends EntidadeNaoEncontradaException{

	private static final long serialVersionUID = 1L;

	public PedidoNaoEncontradoException(String message, Throwable cause) {
		super(message, cause);
	}

	public PedidoNaoEncontradoException(String mensagem) {
		super(mensagem);
	}
	
}
