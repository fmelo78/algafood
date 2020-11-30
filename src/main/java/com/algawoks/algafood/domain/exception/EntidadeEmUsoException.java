package com.algawoks.algafood.domain.exception;

public class EntidadeEmUsoException extends NegocioException{

	private static final long serialVersionUID = 1L;

	public EntidadeEmUsoException(String mensagem) {
		super(mensagem);
	}

	public EntidadeEmUsoException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public EntidadeEmUsoException(String entidade, Long codigo) {
		this(String.format("A entidade %s de código %d está em uso", entidade.toUpperCase(), codigo));
	}

}
