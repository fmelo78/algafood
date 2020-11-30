package com.algawoks.algafood.core.validation;

import java.util.Arrays;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.web.multipart.MultipartFile;

public class FileContentTypeValidator implements ConstraintValidator<FileContentType, MultipartFile>{
	
	private String[] tiposAceitos;
	
//	Método para inicialização do objeto. É como se fosse o contrutor da classe
	@Override
	public void initialize(FileContentType constraintAnnotation) {
		this.tiposAceitos = constraintAnnotation.allowed();
	}
	
	@Override
	public boolean isValid(MultipartFile value, ConstraintValidatorContext context) {
		Boolean resposta = false;
		if (value==null || Arrays.asList(tiposAceitos).contains(value.getContentType())) {
			resposta = true;
		}
		return resposta;
	}

}
