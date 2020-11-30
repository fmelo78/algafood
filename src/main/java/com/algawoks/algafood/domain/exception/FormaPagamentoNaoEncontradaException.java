package com.algawoks.algafood.domain.exception;

public class FormaPagamentoNaoEncontradaException extends EntidadeNaoEncontradaException{
	
	private static final long serialVersionUID = 1L;
	
	public FormaPagamentoNaoEncontradaException (String mensagem) {
		super(mensagem);
	}

	public FormaPagamentoNaoEncontradaException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public FormaPagamentoNaoEncontradaException (Long formaPagamentoId) {
		this(String.format("Forma de Pagamento com código %d não encontrada", formaPagamentoId));
	}
	
}
