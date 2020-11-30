package com.algawoks.algafood.core.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.util.unit.DataSize;
import org.springframework.web.multipart.MultipartFile;

public class FileSizeValidator implements ConstraintValidator<FileSize, MultipartFile>{
	
	private DataSize maxSize;
	
//	Método para inicialização do objeto. É como se fosse o contrutor da classe
	@Override
	public void initialize(FileSize constraintAnnotation) {
		this.maxSize = DataSize.parse(constraintAnnotation.max());
	}
	
	@Override
	public boolean isValid(MultipartFile value, ConstraintValidatorContext context) {
		Boolean resposta = true;
		if (value!=null && value.getSize() > maxSize.toBytes()) {
			resposta = false;
		}
		return resposta;
	}

}
