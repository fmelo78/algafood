package com.algawoks.algafood.domain.exception;

public class UsuarioNaoEncontradoException extends EntidadeNaoEncontradaException{

	private static final long serialVersionUID = 1L;
	
	public UsuarioNaoEncontradoException (String mensagem) {
		super(mensagem);
	}

	public UsuarioNaoEncontradoException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public UsuarioNaoEncontradoException (Long usuarioId) {
		this(String.format("Usuário de código %d não encontrado", usuarioId));
	}
	
}
