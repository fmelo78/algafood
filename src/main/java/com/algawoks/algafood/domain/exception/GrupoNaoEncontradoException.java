package com.algawoks.algafood.domain.exception;

public class GrupoNaoEncontradoException extends EntidadeNaoEncontradaException{

	private static final long serialVersionUID = 1L;
	
	public GrupoNaoEncontradoException (String mensagem) {
		super(mensagem);
	}

	public GrupoNaoEncontradoException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public GrupoNaoEncontradoException (Long grupoId) {
		this(String.format("Grupo de código %d não encontrado", grupoId));
	}
	
}
