package com.algawoks.algafood.domain.exception;

public class RestauranteNaoEncontradoException extends EntidadeNaoEncontradaException{

	private static final long serialVersionUID = 1L;
	
	public RestauranteNaoEncontradoException (String mensagem) {
		super(mensagem);
	}

	public RestauranteNaoEncontradoException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public RestauranteNaoEncontradoException (Long restauranteId) {
		this(String.format("Restaurante de código %d não encontrado", restauranteId));
	}
	
}