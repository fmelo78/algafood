package com.algawoks.algafood.core.validation;

import java.math.BigDecimal;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.ValidationException;

import org.springframework.beans.BeanUtils;

public class ValorZeroIncluiDescricaoValidator implements ConstraintValidator<ValorZeroIncluiDescricao, Object>{

	private String valorField;
	private String descricaoField;
	private String descricaoObrigatoria;
	
	@Override
	public void initialize(ValorZeroIncluiDescricao constraint) {
		this.valorField = constraint.valorField();
		this.descricaoField = constraint.descricaoField();
		this.descricaoObrigatoria = constraint.descricaoObrigatoria();
	}
	
	@Override
	public boolean isValid(Object objeto, ConstraintValidatorContext context) {
		
		boolean valido = true;
		
		try {
//			Busca o valor das propriedades taxaFrete e nome (do restaurante) - estudar Reflections
			BigDecimal valor = (BigDecimal) BeanUtils.getPropertyDescriptor(objeto.getClass(), valorField)
					.getReadMethod().invoke(objeto);
			String descricao = (String) BeanUtils.getPropertyDescriptor(objeto.getClass(), descricaoField)
					.getReadMethod().invoke(objeto);
			if (valor != null && BigDecimal.ZERO.compareTo(valor) == 0 && descricao != null) {
				valido = descricao.toLowerCase().contains(this.descricaoObrigatoria.toLowerCase());
			}			
			return valido;
		} 
		catch (Exception e) {
			throw new ValidationException(e);
		}
	}

}
