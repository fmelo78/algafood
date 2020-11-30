package com.algawoks.algafood.core.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class MultiploValidator implements ConstraintValidator<Multiplo, Number>{
	
	private int numetoMultiplo;

//	Método para inicialização do objeto. É como se fosse o contrutor da classe
	@Override
	public void initialize(Multiplo constraintAnnotation) {
		this.numetoMultiplo = constraintAnnotation.numero();
	}
	
	@Override
	public boolean isValid(Number value, ConstraintValidatorContext context) {
		boolean valido = true;
		if (value != null ) {
			double resto = value.doubleValue()%(double)this.numetoMultiplo;
			if (resto != 0) {
				valido = false;
			}
		}
		return valido;
	}

}
