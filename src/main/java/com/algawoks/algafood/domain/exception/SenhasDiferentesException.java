package com.algawoks.algafood.domain.exception;

public class SenhasDiferentesException extends NegocioException{

	private static final long serialVersionUID = 1L;

	public SenhasDiferentesException(String message, Throwable cause) {
		super(message, cause);
	}

	public SenhasDiferentesException(String message) {
		super(message);
	}

}
